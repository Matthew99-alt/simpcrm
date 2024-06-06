package com.crm.service;

import com.crm.entity.User;
import com.crm.exception.UnauthorizedException;
import com.crm.reposotiry.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;

    public boolean checkUser(String login, String password, List<String> roles) {
        User user = userRepository.findByLoginAndPassword(login, password).orElseThrow(() -> new UnauthorizedException("Некорректные логин или пароль"));
        if (roles.contains("ALL")) {
            return true;
        }
        return roles.contains(user.getRole());
    }
}
