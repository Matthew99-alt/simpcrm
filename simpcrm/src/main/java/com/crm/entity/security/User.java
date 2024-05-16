package com.crm.entity.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    private String username;

    @Column
    private String password;

    @Column
    private Boolean enabled;

    @Column
    private String email;

    @Column(name = "last_activity")
    private String lastActivity;

    @Column
    private Integer priority;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
