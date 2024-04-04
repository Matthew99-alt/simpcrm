package com.crm.reposotiry;

import com.crm.entity.ITService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITServiceRepository extends CrudRepository<ITService, Long>{
    @Override
    List<ITService> findAll();
}
