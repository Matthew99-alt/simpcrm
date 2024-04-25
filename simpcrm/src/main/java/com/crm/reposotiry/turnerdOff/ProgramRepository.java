package com.crm.reposotiry.turnerdOff;

import com.crm.entity.OrderProgram;
import com.crm.entity.Program;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface ProgramRepository { //extends CrudRepository<Program, Long> {
//    @Override
    List<Program> findAll();

    List<Program> findByTitle(String title);

    String findTitleById(Long id);

    List<Program> findByPrice(Long price);
}
