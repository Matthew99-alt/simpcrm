package com.crm.controller;

import com.crm.dto.UserDTO;
import com.crm.service.SecurityService;
import com.crm.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/user")
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers(
            @RequestHeader("login") String login,
            @RequestHeader("password") String password
    ) {
        if (securityService.checkLoginAndPassword(login, password)) {
            return userService.findAllUsers();
        } else {
            return List.of();
        }
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
    public UserDTO editUser(@RequestBody UserDTO userDTO) {
        return userService.editUser(userDTO);
    }

}
