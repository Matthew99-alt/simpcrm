package com.crm.controller.html;

import com.crm.dto.UserDTO;
import com.crm.service.UserService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public String addUser(@ModelAttribute UserDTO user) {
        // Сохраняем пользователя в базе данных
        userService.saveUser(user);
        // Перенаправляем пользователя на другую страницу после успешного добавления
        return "/users/create_user.html";
    }
}
