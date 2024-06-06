package com.crm.controller;

import com.crm.annotation.LoggingMethod;
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

    @LoggingMethod(role = {"admin", "user"})
    @GetMapping("/all")
    public List<MerchandiseDTO> getAllMerchandise(@RequestHeader("login") String login,
                                                  @RequestHeader("password") String password) {
        return merchandiseService.findAllPrograms();
    }

    @LoggingMethod(role = "admin")
    @PostMapping("/save")
    public MerchandiseDTO saveMerchandise(@RequestHeader("login") String login,
                                          @RequestHeader("password") String password,
                                          @RequestBody MerchandiseDTO merchandiseDTO) {
        return merchandiseService.saveMerchandise(merchandiseDTO);
    }

    @LoggingMethod(role = "admin")
    @DeleteMapping("/delete")
    public void deleteMerchandise(@RequestHeader("login") String login,
                                  @RequestHeader("password") String password,
                                  @RequestParam Long id) {
        merchandiseService.deleteMerchandise(id);
    }

    @LoggingMethod(role = "admin")
    @PutMapping("/edit")
    public MerchandiseDTO editMerchandise(@RequestHeader("login") String login,
                                          @RequestHeader("password") String password,
                                          @RequestBody MerchandiseDTO merchandiseDTO) {
        return merchandiseService.editMerchandise(merchandiseDTO);
    }

}
