package com.matheus.sacolaApi.resource;

import com.matheus.sacolaApi.model.Item;
import com.matheus.sacolaApi.resource.dto.ItemDto;
import com.matheus.sacolaApi.service.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ifood-devweek/sacolas")
@RequiredArgsConstructor

public class SacolaResource {
    private final SacolaService sacolaService;




    @PostMapping
    public Item inserirItemSacola(@RequestBody ItemDto itemDto){
        return sacolaService.inserirItemSacola(itemDto);
    }
}
