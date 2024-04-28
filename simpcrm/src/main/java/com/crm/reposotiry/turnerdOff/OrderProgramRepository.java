package com.crm.reposotiry.turnerdOff;

import com.crm.entity.turnedOff.OrderProgram;

import java.util.List;

//@Repository
public interface OrderProgramRepository { // extends CrudRepository<OrderProgram, Long> {
//    @Override
    List<OrderProgram> findAll();

    List<Long> findProgramIdByOrderId(Long id);
}
