package com.crm.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@Table(name = "order_IT_services")
public class OrderITService {
    @Column(value = "IT_services_id")
    private Long ITServicesId;
    @Column(value = "order_id")
    private Long orderId;
}
