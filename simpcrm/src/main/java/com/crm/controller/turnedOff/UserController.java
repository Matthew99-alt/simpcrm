package com.crm.controller.turnedOff;

import com.crm.dto.UserDTO;
import com.crm.entity.User;
import com.crm.service.turnedOff.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/rest/user")
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
    public List<User> getAllUserByFirstName(@RequestBody UserDTO userDTO) {
        return userService.findByFirstName(userDTO.getFirstName());
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
