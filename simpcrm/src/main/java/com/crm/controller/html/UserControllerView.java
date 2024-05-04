package com.crm.controller.html;

import com.crm.dto.UserDTO;
import com.crm.service.UserService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserControllerView {

    private final UserService userService;

    public UserControllerView(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        List<UserDTO> allUsers = userService.findAllUsers();
        model.addAttribute("users", allUsers);
        return "user/users.html";
    }

    @GetMapping("/personalPage/{userId}")
    public String getUser(@PathVariable("userId") Long userId, Model model) {
        UserDTO userDTO = userService.findById(userId);
        model.addAttribute("user", userDTO);
        return "user/personal_page.html";
    }

    @GetMapping("/personalEditPage/{userId}")
    public String getUserEditPage(@PathVariable("userId") Long userId, Model model) {
        UserDTO userDTO = userService.findById(userId);
        model.addAttribute("user", userDTO);
        return "user/edit_user.html";
    }


    @PostMapping("/update")
    public String updateUser(@RequestParam Long userId,
                             @RequestParam String firstName,
                             @RequestParam String email,
                             @RequestParam Long phone,
                             Model model) {
        UserDTO userDTO = userService.editUser(userId, firstName, email, phone);
        model.addAttribute("user", userDTO);
        return "redirect:/users/personalPage/" + userDTO.getId();
    }

}
