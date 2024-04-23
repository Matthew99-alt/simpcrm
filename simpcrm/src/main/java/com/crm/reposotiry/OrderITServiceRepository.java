package com.crm.reposotiry;

import com.crm.entity.OrderITService;
import com.crm.entity.OrderProgram;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderITServiceRepository extends CrudRepository<OrderITService, Long> {
    @Override
    List<OrderITService> findAll();
}
