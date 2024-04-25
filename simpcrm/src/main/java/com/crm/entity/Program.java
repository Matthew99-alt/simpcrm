package com.crm.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
//@Table(name = "program")
public class Program {
    @Id
    private Long id;
//    @Column(value = "title")
    private String title;
//    @Column(value = "description")
    private String description;
//    @Column(value = "price")
    private Long price;

}
