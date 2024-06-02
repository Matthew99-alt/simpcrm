package com.crm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"password","login"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Column(name = "user_role", nullable = false)
    private String role;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetails;

}
