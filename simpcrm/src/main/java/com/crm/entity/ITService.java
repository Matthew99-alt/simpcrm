package com.crm.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
//@Table(name = "IT_services")
public class ITService {

    @Id
    private Long id;

//    @Column(value = "title")
    private String title;

//    @Column(value = "price")
    private Long price;

//    @Column(value = "description")
    private String description;

}
