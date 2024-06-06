package com.crm.controller;

import com.crm.annotation.LoggingMethod;
import com.crm.dto.MerchandiseDTO;
import com.crm.service.MerchandiseService;
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
@RequestMapping("/rest/merchandise")
@RequiredArgsConstructor
public class MerchandiseController {
    private final MerchandiseService merchandiseService;

    @LoggingMethod(role = {"admin", "user"})
    @GetMapping("/all")
    public List<MerchandiseDTO> getAllMerchandise(
            @RequestHeader(value = "password", required = false) String password,
            @RequestHeader(value = "login", required = false) String login
    ) {
        return merchandiseService.findAllPrograms();
    }

    @LoggingMethod(role = "admin")
    @PostMapping("/save")
    public MerchandiseDTO saveMerchandise(
            @RequestHeader(value = "password", required = false) String password,
            @RequestHeader(value = "login", required = false) String login,
            @RequestBody MerchandiseDTO merchandiseDTO
    ) {
        return merchandiseService.saveMerchandise(merchandiseDTO);
    }

    @LoggingMethod(role = "admin")
    @DeleteMapping("/delete")
    public void deleteMerchandise(
            @RequestHeader(value = "password", required = false) String password,
            @RequestHeader(value = "login", required = false) String login,
            @RequestParam Long id
    ) {
        merchandiseService.deleteMerchandise(id);
    }

    @LoggingMethod(role = "admin")
    @PutMapping("/edit")
    public MerchandiseDTO editMerchandise(
            @RequestHeader(value = "password", required = false) String password,
            @RequestHeader(value = "login", required = false) String login,
            @RequestBody MerchandiseDTO merchandiseDTO
    ) {
        return merchandiseService.editMerchandise(merchandiseDTO);
    }

}
