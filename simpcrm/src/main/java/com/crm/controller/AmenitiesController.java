
package com.crm.controller;

import com.crm.dto.AmenitiesDTO;
import com.crm.service.AmenitiesService;
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
    public AmenitiesDTO editAmenities(@RequestBody AmenitiesDTO amenitiesDTO) {
        return amenitiesService.editITService(amenitiesDTO);
    }
}
