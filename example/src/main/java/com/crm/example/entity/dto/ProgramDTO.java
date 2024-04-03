package com.crm.example.entity.dto;

public class ProgramDTO {

    private int id;

    private String title;

    private String description;

    private Integer price;

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription(){
        return description;
    }
    public Integer getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
