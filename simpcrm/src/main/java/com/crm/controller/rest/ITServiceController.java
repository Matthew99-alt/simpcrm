
package com.crm.controller.rest;

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
    public List<ITServiceDTO> getAllITServices() {
        return itServiceService.findAllServices();
    }

    @GetMapping("/title")
    public ITServiceDTO getByTitle(@RequestBody ITServiceDTO itServiceDTO) {
        return itServiceService.findByTitle(itServiceDTO.getTitle());
    }

    @GetMapping("/price")
    public List<ITServiceDTO> getByPrice(@RequestBody ITServiceDTO itServiceDTO) {
        return itServiceService.findByPrice(itServiceDTO.getPrice());
    }

    @GetMapping("/priceWithLimit")
    public List<ITServiceDTO> getPriceWithLimit(@RequestBody ITServiceDTO itServiceDTO){
        return itServiceService.priceMaxToMin(itServiceDTO.getPrice());
    }

    @PostMapping("/save")
    public ITServiceDTO saveITService(@RequestBody ITServiceDTO itServiceDTO) {
        return itServiceService.saveITService(itServiceDTO);
    }

    @DeleteMapping("/delete")
    public void deleteITService(@RequestParam Long id) {
        itServiceService.deleteITService(id);
    }

    @PutMapping("/edit")
    public ITServiceDTO editITService(@RequestBody ITServiceDTO itServiceDTO){ return itServiceService.editITService(itServiceDTO);}
}
