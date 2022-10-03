package com.matheus.sacolaApi.service.Impl;

import com.matheus.sacolaApi.enumeration.FormaPagamento;
import com.matheus.sacolaApi.model.Item;
import com.matheus.sacolaApi.model.Restaurante;
import com.matheus.sacolaApi.model.Sacola;
import com.matheus.sacolaApi.repository.ItemRepository;
import com.matheus.sacolaApi.repository.ProdutoRepository;
import com.matheus.sacolaApi.repository.SacolaRepository;
import com.matheus.sacolaApi.resource.dto.ItemDto;
import com.matheus.sacolaApi.service.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    @Override
    public Sacola verSacola(Long idSacola) {
        return sacolaRepository.findById(idSacola).orElseThrow(
                () -> {
                    throw new RuntimeException("Essa sacola não existe!");
                }
        );
    }

    @Override
    public Sacola fecharSacola(Long idSacola, int numeroformaPagamento) {
        Sacola sacola = verSacola(idSacola);
        if (sacola.getItens().isEmpty()) {
            throw new RuntimeException("Inclua itens na sacola!");
        }
        FormaPagamento formaPagamento = numeroformaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINA_DE_CARTAO;

        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);
        return sacolaRepository.save(sacola);


    }

    @Override
    public Item inserirItemSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getSacolaId());
        if (sacola.isFechada()) {
            throw new RuntimeException("Esta sacola já esta fechada!");
        }

        Item inserirItem = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Produto inexistente!");
                        }
                ))
                .build();

        List<Item> itensSacola = sacola.getItens();

        if (itensSacola.isEmpty()) {
            if (inserirItem.getProduto().getDisponivel()) {
                itensSacola.add(inserirItem);
            } else {
                throw new RuntimeException("Produto Indisponível");
            }


        } else {
            Restaurante restauranteAtualSacola = itensSacola.get(0).getProduto().getRestaurante();
            Restaurante restauranteProduto = inserirItem.getProduto().getRestaurante();

            if (restauranteProduto.equals(restauranteAtualSacola)) {
                if (inserirItem.getProduto().getDisponivel()) {
                    itensSacola.add(inserirItem);
                } else {
                    throw new RuntimeException("Produto Indisponível");
                }
            } else {
                throw new RuntimeException("Você não pode inserir produtos na mesma sacola de restaurantes diferentes.");
            }

        }


        List<Double> valorItens = new ArrayList<>();

        for (Item itemSacola : itensSacola) {
            double valorTotalItem = itemSacola.getProduto().getValorUnitario() * itemSacola.getQuantidade();
            valorItens.add(valorTotalItem);

        }

        Double valorTotalSacola = 0.0;
        for (Double valorCadaItem : valorItens) {
            valorTotalSacola += valorCadaItem;
        }


        sacola.setValorTotal(valorTotalSacola);

        sacolaRepository.save(sacola);
        return inserirItem;
    }
}
