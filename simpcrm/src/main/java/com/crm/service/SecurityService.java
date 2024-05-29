package com.crm.service;

import com.crm.entity.User;
import com.crm.exception.PermissionDeniedException;
import com.crm.reposotiry.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;

    public boolean checkLoginAndPasswordMock(String login, String password) {
        if (Objects.equals(login, "admin") && Objects.equals(password, "admin")) {
            return true;
        } else {
            throw new PermissionDeniedException("Вам сюда нельзя");
        }
    }

    public boolean checkLoginAndPassword(String login, String password) {
        User user = userRepository.findByLoginAndPassword(login, password).orElseThrow(EntityNotFoundException::new);
        return user != null;
    }
}
