package com.crm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity(name = "draft_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "priority")
    private Long priority;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="status_id", referencedColumnName = "id")
    private Status status;

    @Column(name = "description")
    private String description;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    @JoinColumn(name="client_id", referencedColumnName = "id")
    private User client;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany
    @JoinTable(
            name="order_it_service",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "it_service_id")
    )
    private List<ITService> itServices;

    @ManyToMany
    @JoinTable(
            name="order_program",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id")
    )
    private List<Program> programs;

}