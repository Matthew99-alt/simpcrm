package com.crm.controller;

import com.crm.dto.MerchandiseDTO;
import com.crm.exception.PermissionDeniedException;
import com.crm.service.MerchandiseService;

import java.util.List;

import com.crm.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/merchandise")
@RequiredArgsConstructor
public class MerchandiseController {
    private final MerchandiseService merchandiseService;

    private final SecurityService securityService;

    @GetMapping("/all")
    public List<MerchandiseDTO> getAllMerchandise(@RequestHeader("login") String login,
                                                  @RequestHeader("password") String password) {
        if (securityService.checkAdminRole(login, password)) {
            return merchandiseService.findAllPrograms();
        } else {
            throw new PermissionDeniedException("В доступе отказано");
        }
    }

    @PostMapping("/save")
    public MerchandiseDTO saveMerchandise(@RequestHeader("login") String login,
                                          @RequestHeader("password") String password,
                                          @RequestBody MerchandiseDTO merchandiseDTO) {
        if (securityService.checkAdminRole(login, password)) {
            return merchandiseService.saveMerchandise(merchandiseDTO);
        } else {
            throw new PermissionDeniedException("В доступе отказано");
        }
    }

    @DeleteMapping("/delete")
    public void deleteMerchandise(@RequestHeader("login") String login,
                                  @RequestHeader("password") String password,
                                  @RequestParam Long id) {
        if (securityService.checkAdminRole(login, password)) {
            merchandiseService.deleteMerchandise(id);
        } else {
            throw new PermissionDeniedException("В доступе отказано");
        }
    }

    @PutMapping("/edit")
    public MerchandiseDTO editMerchandise(@RequestHeader("login") String login,
                                          @RequestHeader("password") String password,
                                          @RequestBody MerchandiseDTO merchandiseDTO) {
        if (securityService.checkAdminRole(login, password)) {
            return merchandiseService.editMerchandise(merchandiseDTO);
        } else {
            throw new PermissionDeniedException("В доступе отказано");
        }
    }

}
