
package com.crm.controller;

import com.crm.annotation.LoggingMethod;
import com.crm.dto.AmenitiesDTO;
import com.crm.exception.PermissionDeniedException;
import com.crm.service.AmenitiesService;

import java.util.List;

import com.crm.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/amenities")
@RequiredArgsConstructor
public class AmenitiesController {
    private final AmenitiesService amenitiesService;

    @LoggingMethod(role = {"admin", "user"})
    @GetMapping("/all")
    public List<AmenitiesDTO> getAllAmenities(@RequestHeader("login") String login,
                                              @RequestHeader("password") String password) {
        return amenitiesService.findAllAmenities();
    }

    @PostMapping("/save")
    public AmenitiesDTO saveAmenities(@RequestHeader("login") String login,
                                      @RequestHeader("password") String password,
                                      @RequestBody AmenitiesDTO amenitiesDTO) {
        return amenitiesService.saveAmenities(amenitiesDTO);
    }

    @DeleteMapping("/delete")
    public void deleteAmenities(@RequestHeader("login") String login,
                                @RequestHeader("password") String password,
                                @RequestParam Long id) {
        amenitiesService.deleteAmenities(id);
    }

    @PutMapping("/edit")
    public AmenitiesDTO editAmenities(@RequestHeader("login") String login,
                                      @RequestHeader("password") String password,
                                      @RequestBody AmenitiesDTO amenitiesDTO) {
        return amenitiesService.editITService(amenitiesDTO);
    }
}
