package com.crm.controller.rest;

import com.crm.dto.MerchandiseDTO;
import com.crm.service.MerchandiseService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/merchandise")
public class MerchandiseController {
    private final MerchandiseService merchandiseService;

    public MerchandiseController(MerchandiseService merchandiseService) {
        this.merchandiseService = merchandiseService;
    }

    @GetMapping("/all")
    public List<MerchandiseDTO> getAllMerchandise() {
        return merchandiseService.findAllPrograms();
    }

    @PostMapping("/save")
    public MerchandiseDTO saveMerchandise(@RequestBody MerchandiseDTO merchandiseDTO) {
        return merchandiseService.saveMerchandise(merchandiseDTO);
    }

    @DeleteMapping("/delete")
    public void deleteMerchandise(@RequestParam Long id) {
        merchandiseService.deleteMerchandise(id);
    }

    @PutMapping("/edit")
    public MerchandiseDTO editMerchandise(@RequestBody MerchandiseDTO merchandiseDTO) {
        return merchandiseService.editMerchandise(merchandiseDTO);
    }

}
