package com.crm.example.reposotory;

import com.crm.example.entity.model.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Override
    List<User> findAll();
    @Query("SELECT * FROM \"user\" where first_name = :first_name")
    List<User> findByFirstName(@Param("first_name") String first_name);
}
