package com.matheus.sacolaApi.service;

import com.matheus.sacolaApi.model.Item;
import com.matheus.sacolaApi.model.Sacola;
import com.matheus.sacolaApi.resource.dto.ItemDto;

public interface SacolaService {
    Sacola verSacola(Long id);
    Sacola fecharSacola(Long id, int formaPagamento);
    Item inserirItemSacola(ItemDto itemDto);

}
