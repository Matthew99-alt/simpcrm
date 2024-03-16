package com.crm.example.model;

abstract class Data {
    private final String title;
    private final String description;
    private final Integer price;

    public Data(String title,
                String description,
                Integer price)
    {
        this.title = title;
        this.description = description;
        this.price = price;

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
}
