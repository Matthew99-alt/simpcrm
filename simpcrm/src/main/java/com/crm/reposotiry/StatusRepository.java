package com.crm.reposotiry;

import com.crm.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository  extends JpaRepository<Status, Long> {

    List<Status> findAll();

}
