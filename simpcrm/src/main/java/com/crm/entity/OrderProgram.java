package com.crm.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@Table(name = "order_program")
public class OrderProgram {
    @Column(value = "id_program")
    private Long IdProgram;
    @Column(value = "id_order")
    private Long IdOrder;
}