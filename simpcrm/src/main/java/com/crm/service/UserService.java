package com.crm.service;

import com.crm.dto.UserDTO;
import com.crm.entity.User;
import com.crm.reposotiry.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(makeAnUserDTO(new UserDTO(), user));
        }
        return userDTOS;
    }

    private UserDTO makeAnUserDTO(UserDTO userDTO, User user) {
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setSecondName(user.getSecondName());
        userDTO.setMiddleName(user.getMiddleName());
        userDTO.setAddress(user.getAddress());
        userDTO.setPhone(user.getPhone());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserType(user.getUserType());
        userDTO.setLocality(user.getLocality());

        return userDTO;
    }

    private User makeAnUser(UserDTO userDTO, User user) {
        userDTO.setId(user.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setSecondName(userDTO.getSecondName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setUserType(userDTO.getUserType());
        user.setLocality(userDTO.getLocality());

        return user;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        userRepository.save(makeAnUser(userDTO, user));
        userDTO.setId(user.getId());
        return userDTO;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO editUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        userRepository.save(makeAnUser(userDTO, user));
        return userDTO;
    }

    public UserDTO editUser(Long userId, String firstName, String email, Long phone) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        user.setFirstName(firstName);
        user.setEmail(email);
        user.setPhone(phone);
        return makeAnUserDTO(new UserDTO(), userRepository.save(user));
    }

    public UserDTO findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        return makeAnUserDTO(new UserDTO(), user);
    }
}
