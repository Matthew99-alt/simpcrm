package com.crm.reposotiry.turnerdOff;

import com.crm.entity.OrderProgram;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface OrderProgramRepository { // extends CrudRepository<OrderProgram, Long> {
//    @Override
    List<OrderProgram> findAll();

    List<Long> findProgramIdByOrderId(Long id);
}
