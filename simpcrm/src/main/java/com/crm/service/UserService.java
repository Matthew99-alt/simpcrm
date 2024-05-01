package com.crm.service;

import com.crm.dto.UserDTO;
import com.crm.entity.User;
import com.crm.reposotiry.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;

    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(makeAnUserDTO(new UserDTO(), user));
        }
        return userDTOS;
    }

    public UserDTO findByFirstNameAndSecondNameAndMiddleName(String firstName, String secondName, String middleName) {
        User user = userRepository.findByFirstNameAndSecondNameAndMiddleName(firstName, secondName, middleName);
        return makeAnUserDTO(new UserDTO(),user);
    }

    private UserDTO makeAnUserDTO(UserDTO userDTO, User user){
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setSecondName(user.getSecondName());
        userDTO.setMiddleName(user.getMiddleName());
        userDTO.setAddress(user.getAddress());
        userDTO.setPhone(user.getPhone());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }

    private User makeAnUser(UserDTO userDTO, User user){
        user.setFirstName(userDTO.getFirstName());
        user.setSecondName(userDTO.getSecondName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());

        return user;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        userRepository.save(makeAnUser(userDTO, user));
        return userDTO;
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO editUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        userRepository.save(makeAnUser(userDTO, user));
        return userDTO;
    }
}
