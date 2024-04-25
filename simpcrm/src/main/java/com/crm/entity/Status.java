package com.crm.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
//@Table(name = "status")
public class Status {
    @Id
    public Long id;
//    @Column(value = "status")
    public String status;

}
