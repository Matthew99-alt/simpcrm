package com.crm.example.controller;

import com.crm.example.entity.dto.ProgramDTO;
import com.crm.example.entity.dto.UserDTO;
import com.crm.example.entity.model.User;
import com.crm.example.service.UserService;
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
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/name")
    public List<User> getUsersByName(@RequestBody String first_name) {
        return userService.findByFirstName(first_name);
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody UserDTO userDTO) {
        userService.deleteUser(userDTO);
    }

    @PutMapping("/edit")
    public User editUser(@RequestBody UserDTO userDTO){ return userService.editUser(userDTO);}
}
