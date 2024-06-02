
package com.crm.controller;

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
    private final SecurityService securityService;


    @GetMapping("/all")
    public List<AmenitiesDTO> getAllAmenities(@RequestHeader("login") String login,
                                              @RequestHeader("password") String password) {
        return amenitiesService.findAllAmenities();
//        if (securityService.checkAdminRole(login, password, loggingMethod.role())) {
//            return amenitiesService.findAllAmenities();
//        } else {
//            throw new PermissionDeniedException("В доступе отказано");
//        }

    }

    @PostMapping("/save")
    public AmenitiesDTO saveAmenities(@RequestHeader("login") String login,
                                      @RequestHeader("password") String password,
                                      @RequestBody AmenitiesDTO amenitiesDTO) {
        return amenitiesService.saveAmenities(amenitiesDTO);
//        if (securityService.checkAdminRole(login, password, loggingMethod.role())) {
//            return amenitiesService.saveAmenities(amenitiesDTO);
//        } else {
//            throw new PermissionDeniedException("В доступе отказано");
//        }

    }

    @DeleteMapping("/delete")
    public void deleteAmenities(@RequestHeader("login") String login,
                                @RequestHeader("password") String password,
                                @RequestParam Long id) {
        amenitiesService.deleteAmenities(id);
//        if (securityService.checkAdminRole(login, password, loggingMethod.role())) {
//            amenitiesService.deleteAmenities(id);
//        } else {
//            throw new PermissionDeniedException("В доступе отказано");
//        }
    }

    @PutMapping("/edit")
    public AmenitiesDTO editAmenities(@RequestHeader("login") String login,
                                      @RequestHeader("password") String password,
                                      @RequestBody AmenitiesDTO amenitiesDTO) {
        return amenitiesService.editITService(amenitiesDTO);
//        if (securityService.checkAdminRole(login, password, loggingMethod.role())) {
//            return amenitiesService.editITService(amenitiesDTO);
//        } else {
//            throw new PermissionDeniedException("В доступе отказано");
//        }
    }
}
