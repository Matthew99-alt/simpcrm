package com.crm.service;

import com.crm.dto.UserDTO;
import com.crm.entity.User;
import com.crm.entity.UserDetails;
import com.crm.exception.MyEntityNotFoundException;
import com.crm.reposotiry.UserDetailsRepository;
import com.crm.reposotiry.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserDetailsRepository userDetailsRepository;

    public List<UserDTO> findAllUsers() {
        List<User> userEntities = userRepository.findAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User user : userEntities) {
            userDTOS.add(makeAnUserDTO(new UserDTO(), user));
        }
        return userDTOS;
    }

    public UserDTO findByUserLoginAndPassword(String login, String password){
        return makeAnUserDTO(new UserDTO(), userRepository.findByLoginAndPassword(login, password)
                .orElseThrow(()-> new MyEntityNotFoundException("Пользователь не найден")));
    }

    private UserDTO makeAnUserDTO(UserDTO userDTO, User user) {
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());

        UserDetails userDetails = user.getUserDetails();
        if (userDetails != null) {
            userDTO.setFirstName(userDetails.getFirstName());
            userDTO.setSecondName(userDetails.getSecondName());
            userDTO.setMiddleName(userDetails.getMiddleName());
            userDTO.setAddress(userDetails.getAddress());
            userDTO.setPhone(userDetails.getPhone());
            userDTO.setEmail(userDetails.getEmail());
            userDTO.setCity(userDetails.getCity());
        }

        userDTO.setRole(user.getRole());

        return userDTO;
    }

    private User makeAnUser(UserDTO userDTO, User user) {
        userDTO.setId(user.getId());
        user.setRole(userDTO.getRole());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());

        UserDetails userDetails = user.getUserDetails();
        if (userDetails == null) {
            // Если UserDetails не существует, создайте новый объект
            userDetails = new UserDetails();
            user.setUserDetails(userDetails); // Установите новый объект UserDetails в User
        }
        userDetails.setFirstName(userDTO.getFirstName());
        userDetails.setSecondName(userDTO.getSecondName());
        userDetails.setMiddleName(userDTO.getMiddleName());
        userDetails.setAddress(userDTO.getAddress());
        userDetails.setPhone(userDTO.getPhone());
        userDetails.setEmail(userDTO.getEmail());
        userDetails.setCity(userDTO.getCity());

        return user;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        UserDetails userDetails = new UserDetails();
        user.setUserDetails(userDetails);

        userDetailsRepository.save(makeAnUser(userDTO, user).getUserDetails());

        userRepository.save(makeAnUser(userDTO, user));
        userDTO.setId(user.getId());
        return userDTO;
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException("Данный пользователь не найден"));

        Long userDetailsId = user.getUserDetails().getId();

        userRepository.deleteById(id);
        userDetailsRepository.deleteById(userDetailsId);
    }

    public UserDTO editUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElseThrow(() -> new MyEntityNotFoundException("Данный пользователь не найден"));
        user.setId(userDTO.getId());

        userRepository.save(makeAnUser(userDTO, user));
        return userDTO;
    }

    public UserDTO findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new MyEntityNotFoundException("Данный пользователь не найден"));
        return makeAnUserDTO(new UserDTO(), user);
    }
}
