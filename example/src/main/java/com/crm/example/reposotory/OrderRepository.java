package com.crm.example.reposotory;

import com.crm.example.entity.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
    @Override
    List<Order> findAll();
}
