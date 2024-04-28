package com.crm.reposotiry;

import com.crm.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    List<Program> findAll();

    List<Program> findByTitle(String title);

    String findTitleById(Long id);

    List<Program> findByPrice(Long price);
}
