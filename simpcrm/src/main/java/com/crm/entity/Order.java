package com.crm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


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
    @Column(name = "status_id")
    private Long statusId;
    @Column(name = "description")
    private String description;
    @Column(name = "comments")
    private String comments;
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "user_id")
    private Long userId;
}



/*
//TODO: ПОСЛЕ того как заработает Order как есть, переделать
// Status так же рабочим
// ТОЛЬКО ПОСЛЕ ЭТОГО сделать привязку как ниже

@Column(value = "status_id")
    @JoinColumn(name = "id")
    private Status statusId;
 */