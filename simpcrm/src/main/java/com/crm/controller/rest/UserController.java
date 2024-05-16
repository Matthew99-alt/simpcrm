package com.crm.controller.rest;

import com.crm.dto.UserDTO;
import com.crm.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/save")
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/edit")
    public UserDTO editUser(@RequestBody UserDTO userDTO){ return userService.editUser(userDTO);}
}
