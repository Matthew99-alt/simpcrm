package com.crm.controller.rest;

import com.crm.dto.MerchandiseDTO;
import com.crm.service.MerchandiseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public MerchandiseDTO editMerchandise(@RequestBody MerchandiseDTO merchandiseDTO){ return merchandiseService.editMerchandise(merchandiseDTO);}

}
