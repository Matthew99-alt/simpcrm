package com.crm.service;

import com.crm.dto.UserDTO;
import com.crm.entity.User;
import com.crm.entity.UserDetails;
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
        List<User> userEntities = userRepository.findAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User user : userEntities) {
            userDTOS.add(makeAnUserDTO(new UserDTO(), user));
        }
        return userDTOS;
    }

    private UserDTO makeAnUserDTO(UserDTO userDTO, User user) {
        UserDetails userDetails = user.getUserDetails();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
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
        user.getUserDetails().setFirstName(userDTO.getFirstName());
        user.getUserDetails().setSecondName(userDTO.getSecondName());
        user.getUserDetails().setMiddleName(userDTO.getMiddleName());
        user.getUserDetails().setAddress(userDTO.getAddress());
        user.getUserDetails().setPhone(userDTO.getPhone());
        user.getUserDetails().setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.getUserDetails().setCity(userDTO.getCity());

        return user;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        UserDetails userDetails = new UserDetails();
        user.setUserDetails(userDetails);

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
