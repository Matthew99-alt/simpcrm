package com.crm.service;

import com.crm.dto.UserDTO;
import com.crm.entity.User;
import com.crm.entity.UserDetails;
import com.crm.reposotiry.UserDetailsRepository;
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

    private final UserDetailsRepository userDetailsRepository;

    public List<UserDTO> findAllUsers() {
        List<User> userEntities = userRepository.findAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User user : userEntities) {
            userDTOS.add(makeAnUserDTO(new UserDTO(), user));
        }
        return userDTOS;
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
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        Long userDetailsId = user.getUserDetails().getId();

        userRepository.deleteById(id);
        userDetailsRepository.deleteById(userDetailsId);
    }

    public UserDTO editUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElseThrow(EntityNotFoundException::new);
        user.setId(userDTO.getId());

        userRepository.save(makeAnUser(userDTO, user));
        return userDTO;
    }

    public UserDTO findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        return makeAnUserDTO(new UserDTO(), user);
    }
}
