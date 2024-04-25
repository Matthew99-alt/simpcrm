package com.crm.reposotiry.turnerdOff;

import com.crm.entity.OrderITService;
import com.crm.entity.OrderProgram;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface OrderITServiceRepository {  //extends CrudRepository<OrderITService, Long> {
//    @Override
    List<OrderITService> findAll();

    List<OrderITService> findAllById(Long id);
    List<Long> findITServiceIdByOrderId(Long id);

    List<OrderITService> findByTitle(String title);
}
