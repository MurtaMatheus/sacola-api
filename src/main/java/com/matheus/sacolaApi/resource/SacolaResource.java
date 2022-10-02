package com.matheus.sacolaApi.resource;

import com.matheus.sacolaApi.model.Item;
import com.matheus.sacolaApi.model.Sacola;
import com.matheus.sacolaApi.resource.dto.ItemDto;
import com.matheus.sacolaApi.service.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ifood-devweek/sacolas")
@RequiredArgsConstructor

public class SacolaResource {
    private final SacolaService sacolaService;




    @PostMapping
    public Item inserirItemSacola(@RequestBody ItemDto itemDto){
        return sacolaService.inserirItemSacola(itemDto);
    }

    @GetMapping("/{idSacola}")
    public Sacola verSacola(@PathVariable("idSacola") Long idSacola){
        return sacolaService.verSacola(idSacola);
    }

    @PatchMapping("fecharSacola/{idSacola}")
    public Sacola fecharSacola(@PathVariable("idSacola") Long idSacola,
                               @RequestParam("formaPagamento") int formaPagamento){
        return sacolaService.fecharSacola(idSacola, formaPagamento);
    }
}
