package com.crm.reposotiry;

import com.crm.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAll();

    List<Order> findOrdersByClientLoginAndClientPassword(String login, String password);
}
