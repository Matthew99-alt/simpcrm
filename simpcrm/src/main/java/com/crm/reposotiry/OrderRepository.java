package com.crm.reposotiry;

import com.crm.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Override
    List<Order> findAll();

    Order findOneById(Long id);

    List<Order> findByUserId(Long userId);
    List<Order> findByOrderName(String orderName);

    List<Order> findByPriority(Long priority);
}
