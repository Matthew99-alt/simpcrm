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

    public List<UserDTO> findByUserType(String userType) {
        List<User> userList = userRepository.findByUserType(userType);
        ArrayList<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(makeAnUserDTO(new UserDTO(), user));
        }
        return userDTOList;
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

    public UserDTO findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        return makeAnUserDTO(new UserDTO(), user);
    }
}
