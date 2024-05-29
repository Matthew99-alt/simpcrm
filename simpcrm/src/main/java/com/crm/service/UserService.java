package com.crm.service;

import com.crm.dto.UserDTO;
import com.crm.entity.UserEntity;
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
        List<UserEntity> userEntityEntities = userRepository.findAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity userEntity : userEntityEntities) {
            userDTOS.add(makeAnUserDTO(new UserDTO(), userEntity));
        }
        return userDTOS;
    }

    private UserDTO makeAnUserDTO(UserDTO userDTO, UserEntity userEntity) {
        userDTO.setId(userEntity.getId());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setSecondName(userEntity.getSecondName());
        userDTO.setMiddleName(userEntity.getMiddleName());
        userDTO.setAddress(userEntity.getAddress());
        userDTO.setPhone(userEntity.getPhone());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setUserType(userEntity.getUserType());
        userDTO.setLocality(userEntity.getLocality());

        return userDTO;
    }

    private UserEntity makeAnUser(UserDTO userDTO, UserEntity userEntity) {
        userDTO.setId(userEntity.getId());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setSecondName(userDTO.getSecondName());
        userEntity.setMiddleName(userDTO.getMiddleName());
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setUserType(userDTO.getUserType());
        userEntity.setLocality(userDTO.getLocality());

        return userEntity;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userRepository.save(makeAnUser(userDTO, userEntity));
        userDTO.setId(userEntity.getId());
        return userDTO;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO editUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userRepository.save(makeAnUser(userDTO, userEntity));
        return userDTO;
    }

    public UserDTO findById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        return makeAnUserDTO(new UserDTO(), userEntity);
    }
}
