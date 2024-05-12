package com.crm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "amenities")
public class Amenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Long price;

    @Column(name = "description")
    private String description;

    @Column(name = "ratio")
    private double ratio;
}
