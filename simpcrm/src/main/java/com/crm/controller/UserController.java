package com.crm.controller;

import com.crm.dto.UserDTO;
import com.crm.exception.PermissionDeniedException;
import com.crm.service.SecurityService;
import com.crm.service.UserService;
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

@RestController
@RequestMapping("/rest/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    @GetMapping("/all")
    public List<UserDTO> getAllUsers(
            @RequestHeader("login") String login,
            @RequestHeader("password") String password
    ) {
        if (securityService.checkAdminRole(login, password)) {
            return userService.findAllUsers();
        } else {
            throw new PermissionDeniedException("В доступе отказано");
        }
    }

    @PostMapping("/save")
    public UserDTO saveUser(@RequestHeader("login") String login,
                            @RequestHeader("password") String password,
                            @RequestBody UserDTO userDTO) {
        if (securityService.checkAdminRole(login, password)) {
            return userService.saveUser(userDTO);
        } else {
            throw new PermissionDeniedException("В доступе отказано");
        }
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestHeader("login") String login,
                           @RequestHeader("password") String password,
                           @RequestParam("id") Long id) {
        if (securityService.checkAdminRole(login, password)) {
        userService.deleteUser(id);}
        else {
            throw new PermissionDeniedException("В доступе отказано");
        }
    }

    @PutMapping("/edit")
    public UserDTO editUser(@RequestHeader("login") String login,
                            @RequestHeader("password") String password,
                            @RequestBody UserDTO userDTO) {
        if (securityService.checkAdminRole(login, password)) {
            return userService.editUser(userDTO);
        } else {
            throw new PermissionDeniedException("В доступе отказано");
        }
    }

}
