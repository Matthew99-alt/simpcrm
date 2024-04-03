package com.crm.example.reposotory;

import com.crm.example.entity.model.ITService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITServiceRepository extends CrudRepository<ITService, String>{
    @Override
    List<ITService> findAll();
}
