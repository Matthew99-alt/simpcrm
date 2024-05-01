package com.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity(name = "draft_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String secondName;
    @Column(name = "second_name")
    private String middleName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private Long phone;
    @Column(name = "address")
    private String address;
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    public List<Order> client;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    public List<Order> users;
}
