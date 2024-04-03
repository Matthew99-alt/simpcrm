package com.crm.example.reposotory;

import com.crm.example.entity.model.Program;
import com.crm.example.entity.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends CrudRepository<Program, String> {
    @Override
    List<Program> findAll();
}
