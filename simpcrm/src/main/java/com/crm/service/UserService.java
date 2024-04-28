package com.crm.service;

import com.crm.dto.UserDTO;
import com.crm.entity.User;
import com.crm.reposotiry.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<User> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
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

    public User saveUser(UserDTO userDTO) {
        User user = new User();
        return userRepository.save(makeAnUser(userDTO, user));
    }
    public void deleteUser(UserDTO userDTO) {
        userRepository.deleteById(userDTO.getId());
    }

    public User editUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        return userRepository.save(makeAnUser(userDTO, user));
    }

}
