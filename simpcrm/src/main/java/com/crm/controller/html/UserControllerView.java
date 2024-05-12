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

    @GetMapping("/personalEditPage/{userId}")
    public String getUserEditPage(@PathVariable("userId") Long userId, Model model) {
        UserDTO userDTO = userService.findById(userId);
        model.addAttribute("user", userDTO);
        return "user/edit_user.html";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute UserDTO user,
                         @RequestParam("userId") Long userId,
                         Model model) {
        user.setId(userId); // Устанавливаем ID пользователя в объект UserDTO
        UserDTO updatedUser = userService.editUser(user);
        model.addAttribute("user", updatedUser);
        return "redirect:/users/personalPage/" + updatedUser.getId();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
