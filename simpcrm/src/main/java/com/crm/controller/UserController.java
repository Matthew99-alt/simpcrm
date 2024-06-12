package com.crm.controller;

import com.crm.annotation.LoggingMethod;
import com.crm.dto.UserDTO;
import com.crm.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/rest/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @LoggingMethod(role = {"admin", "user"})
    @GetMapping("/all")
    public List<UserDTO> getAllUsers(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password
    ) {
        return userService.findAllUsers();
    }

    @PostMapping("/save")
    public UserDTO saveUser(
            @RequestBody UserDTO userDTO
    ) {
        return userService.saveUser(userDTO);
    }

    @LoggingMethod(role = {"admin"})
    @DeleteMapping("/delete")
    public void deleteUser(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @RequestParam("id") Long id
    ) {
        userService.deleteUser(id);
    }

    @LoggingMethod(role = "admin")
    @PutMapping("/edit")
    public UserDTO editUser(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @RequestBody UserDTO userDTO
    ) {
        return userService.editUser(userDTO);
    }

    @LoggingMethod(role = "admin")
    @GetMapping("/loginAndPassword")
    public UserDTO findByLoginAndPassword(@RequestParam String login, @RequestParam String password){
        return userService.findByUserLoginAndPassword(login, password);
    }
}
