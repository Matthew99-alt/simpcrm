package com.crm.example.entity.dto;

import org.springframework.data.relational.core.mapping.Column;

public class ITServiceDTO {
    private int id;

    private String title;

    private Integer price;

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
