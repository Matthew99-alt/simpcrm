package com.crm.example.model;

public class Product extends Data{

    private final String imageUrl;

    public Product(String title, String description, Integer price, String imageUrl) {
        super(title, description, price);
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
