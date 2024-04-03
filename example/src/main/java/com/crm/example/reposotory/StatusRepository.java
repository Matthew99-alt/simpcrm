package com.crm.example.reposotory;

import com.crm.example.entity.model.Program;
import com.crm.example.entity.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends CrudRepository<Status, String> {
    @Override
    List<Status> findAll();
}
