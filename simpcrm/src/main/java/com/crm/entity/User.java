package com.crm.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
//@Table(name = "user")
public class User {
    @Id
    private Long id;
//    @Column(value = "first_name")
    private String firstName;
//    @Column(value = "middle_name")
    private String secondName;
//    @Column(value = "second_name")
    private String middleName;
//    @Column(value = "email")
    private String email;
//    @Column(value = "phone")
    private Long phone;
//    @Column(value = "address")
    private String address;

}
