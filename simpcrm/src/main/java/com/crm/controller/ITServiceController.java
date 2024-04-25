package com.crm.controller;

import com.crm.dto.ITServiceDTO;
import com.crm.entity.ITService;
import com.crm.service.ITServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/itservice")
public class ITServiceController {
    private final ITServiceService itServiceService;

    public ITServiceController(ITServiceService itServiceService) {
        this.itServiceService = itServiceService;
    }

    @GetMapping("/all")
    public List<ITService> getAllITServices() {
        return itServiceService.findAllServices();
    }

    @GetMapping("/title")
    public List<ITService> getByTitle(@RequestBody ITServiceDTO itServiceDTO) {
        return itServiceService.findByTitle(itServiceDTO.getTitle());
    }

    @GetMapping("/price")
    public List<ITService> getByPrice(@RequestBody ITServiceDTO itServiceDTO) {
        return itServiceService.findByPrice(itServiceDTO.getPrice());
    }

    @GetMapping("/priceWithLimit")
    public List<ITService> getPriceWithLimit(@RequestBody ITServiceDTO itServiceDTO){
        return itServiceService.priceMaxToMin(itServiceDTO.getPrice());
    }

    @PostMapping("/save")
    public ITService saveITService(@RequestBody ITServiceDTO itServiceDTO) {
        return itServiceService.saveITService(itServiceDTO);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody ITServiceDTO itServiceDTO) {
        itServiceService.deleteITService(itServiceDTO);
    }

    @PutMapping("/edit")
    public ITService editUser(@RequestBody ITServiceDTO itServiceDTO){ return itServiceService.editUser(itServiceDTO);}
}
