package com.crm.reposotiry.turnerdOff;

import com.crm.entity.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

//@Repository
public interface UserRepository { //extends CrudRepository<User, Long> {
//    @Override
    List<User> findAll();
    List<User> findByFirstName(String firstName);
    String findFirstNameById(Long firstName);
    User findOneById(Long id);
}
