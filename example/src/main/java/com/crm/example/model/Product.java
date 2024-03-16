package com.crm.example.model;

public class Product {

    private final String imageUrl;
    private final String name;
    private final Integer price;
    private final String description;

    public Product(String imageUrl, String name, Integer price, String description) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.description = description;
        this.price = price;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
