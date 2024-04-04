package com.crm.reposotiry;

import com.crm.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jdbc.repository.query.JdbcQueryMethod;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();
    @Query("select u from User u where u.firstName = :first_name")
    List<User> findByFirstName(@Param("first_name") String first_name);


}
