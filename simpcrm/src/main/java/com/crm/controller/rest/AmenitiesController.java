
package com.crm.controller.rest;

import com.crm.dto.AmenitiesDTO;
import com.crm.service.AmenitiesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/amenities")
public class AmenitiesController {
    private final AmenitiesService amenitiesService;

    public AmenitiesController(AmenitiesService amenitiesService) {
        this.amenitiesService = amenitiesService;
    }

    @GetMapping("/all")
    public List<AmenitiesDTO> getAllAmenities() {
        return amenitiesService.findAllAmenities();
    }

    @PostMapping("/save")
    public AmenitiesDTO saveAmenities(@RequestBody AmenitiesDTO amenitiesDTO) {
        return amenitiesService.saveAmenities(amenitiesDTO);
    }

    @DeleteMapping("/delete")
    public void deleteAmenities(@RequestParam Long id) {
        amenitiesService.deleteAmenities(id);
    }

    @PutMapping("/edit")
    public AmenitiesDTO editAmenities(@RequestBody AmenitiesDTO amenitiesDTO){ return amenitiesService.editITService(amenitiesDTO);}
}
