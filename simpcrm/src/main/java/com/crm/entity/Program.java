package com.crm.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "program")
public class Program {
    @Id
    private Long id;
    @Column(value = "title")
    private String title;
    @Column(value = "description")
    private String description;
    @Column(value = "price")
    private Long price;

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription(){
        return description;
    }
    public Long getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
