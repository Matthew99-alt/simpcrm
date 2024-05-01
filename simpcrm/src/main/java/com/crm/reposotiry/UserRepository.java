package com.crm.reposotiry;

import com.crm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    List<User> findByFirstName(String firstName);

    String findFirstNameById(Long firstName);

    User findOneById(Long id);

    User findByFirstNameAndSecondNameAndMiddleName(String firstName, String secondName, String middleName);
}
