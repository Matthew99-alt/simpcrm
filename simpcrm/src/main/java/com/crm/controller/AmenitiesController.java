
package com.crm.controller;

import com.crm.annotation.LoggingMethod;
import com.crm.dto.AmenitiesDTO;
import com.crm.service.AmenitiesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/rest/amenities")
@RequiredArgsConstructor
public class AmenitiesController {
    private final AmenitiesService amenitiesService;

    @LoggingMethod(role = {"admin", "user"})
    @GetMapping("/all")
    public List<AmenitiesDTO> getAllAmenities(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password
    ) {
        return amenitiesService.findAllAmenities();
    }

    @LoggingMethod(role = "admin")
    @PostMapping("/save")
    public AmenitiesDTO saveAmenities(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @RequestBody AmenitiesDTO amenitiesDTO
    ) {
        return amenitiesService.saveAmenities(amenitiesDTO);
    }

    @LoggingMethod(role = "admin")
    @DeleteMapping("/delete")
    public void deleteAmenities(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @RequestParam Long id
    ) {
        amenitiesService.deleteAmenities(id);
    }

    @LoggingMethod(role = "admin")
    @PutMapping("/edit")
    public AmenitiesDTO editAmenities(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @RequestBody AmenitiesDTO amenitiesDTO
    ) {
        return amenitiesService.editITService(amenitiesDTO);
    }
}
