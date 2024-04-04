package com.crm.reposotiry;

import com.crm.entity.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {
    @Override
    List<Status> findAll();
}
