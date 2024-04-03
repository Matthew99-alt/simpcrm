package com.crm.example.controller;

import com.crm.example.entity.dto.ITServiceDTO;
import com.crm.example.entity.dto.OrderDTO;
import com.crm.example.entity.dto.UserDTO;
import com.crm.example.entity.model.ITService;
import com.crm.example.entity.model.Status;
import com.crm.example.entity.model.User;
import com.crm.example.service.ITServiceService;
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
        return itServiceService.findAllUsers();
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
