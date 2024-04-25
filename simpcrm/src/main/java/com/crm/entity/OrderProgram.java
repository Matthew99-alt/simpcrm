package com.crm.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@Table(name = "order_program")
public class OrderProgram {
//    @Column(value = "id_program")
    private Long idProgram;
//    @Column(value = "id_order")
    private Long idOrder;
}