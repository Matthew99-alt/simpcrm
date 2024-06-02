package com.crm.service;

import com.crm.entity.User;
import com.crm.exception.PermissionDeniedException;
import com.crm.exception.UnauthorizedException;
import com.crm.reposotiry.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;


    //TODO:удалить
    public boolean checkLoginAndPasswordMock(String login, String password) {
        if (Objects.equals(login, "admin") && Objects.equals(password, "admin")) {
            return true;
        } else {
            throw new PermissionDeniedException("Вам сюда нельзя");
        }
    }

    public boolean checkUser(String login, String password, List<String> roles) {
        User user = userRepository.findByLoginAndPassword(login, password).orElseThrow(() -> new UnauthorizedException("Некорректные логин или пароль"));
        if (roles.contains("ALL"))
            return true;
        return roles.contains(user.getRole());
    }


    //TODO:удалить
    public boolean checkAdminRole(String login, String password, String role) {
        User user = userRepository.findByLoginAndPassword(login, password).orElseThrow(() -> new UnauthorizedException("Некорректные логин или пароль"));
        if (!Objects.equals(user.getRole(), role)) {
            throw new UnauthorizedException("Пользователю с вашим уровнем доступа, действие запрещено");
        }

        return true;
    }

    //TODO:удалить
    public boolean checkAdminAndUserRole(String login, String password) {
        User user = userRepository.findByLoginAndPassword(login, password).orElseThrow(EntityNotFoundException::new);
        return Objects.equals(user.getRole(), "admin") && Objects.equals(user.getRole(), "user");
    }
}
