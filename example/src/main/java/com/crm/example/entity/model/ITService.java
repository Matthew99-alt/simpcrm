package com.crm.example.entity.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "IT_services")
public class ITService {
    @Id
    private int id;
    @Column(value = "title")
    private String title;
    @Column(value = "price")
    private Integer price;
    @Column(value = "description")
    private String description;


    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public Integer getPrice() {
        return price;
    }
    public String getDescription(){
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
