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

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="status_id", referencedColumnName = "id")
    private Status status;

    @Column(name = "description")
    private String description;

    @Column(name = "comments")
    private String comments;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="client_id", referencedColumnName = "id")
    private User client;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name="order_amenities",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "amenities_id")
    )
    private List<Amenities> amenities;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name="order_merchandise",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "merchandise_id")
    )
    private List<Merchandise> merchandises;

    @Column(name = "total_number_of_merchandises")
    private int totalNumberOfMerchandises;

    @Column(name = "total_number_of_amenities")
    private int totalNumberOfAmenities;

    @Column(name = "total_cost")
    private double totalCost;
}