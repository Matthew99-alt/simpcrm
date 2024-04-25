package com.crm.service.turnedOff;

import com.crm.dto.UserDTO;
import com.crm.entity.User;
import com.crm.reposotiry.turnerdOff.UserRepository;

import java.util.List;

//@Service
public class UserService {
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<User> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }


    private User makeAnUser(UserDTO userDTO, boolean idEnable){
        User user = new User();
        if(idEnable){
            user.setId(userDTO.getId());
        }
        user.setFirstName(userDTO.getFirstName());
        user.setSecondName(userDTO.getSecondName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());

        return user;
    }

    public User saveUser(UserDTO userDTO) {
//        return userRepository.save(makeAnUser(userDTO, false));
        return null;
    }
    public void deleteUser(UserDTO userDTO) {
//        userRepository.delete(makeAnUser(userDTO, true));
    }

    public User editUser(UserDTO userDTO){
//        return userRepository.save(makeAnUser(userDTO, true));
        return null;
    }

}
