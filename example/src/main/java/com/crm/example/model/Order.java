package com.crm.example.model;

import java.util.ArrayList;

public class Order {
    private final String order_name;
    private final long priority;
    private final String status;
    private ArrayList<String> comments = new ArrayList<>();
    private final String description;
    private final ArrayList<ITService> ITServices;
    private final ArrayList<Program> programs;

    public Order(
            String order_name,
            long priority,
            String status,
            ArrayList<String> comments,
            String description, ArrayList<ITService> itServices, ArrayList<Program> programs
    ){
        this.order_name = order_name;
        this.priority = priority;
        this.status = status;
        this.comments = comments;
        this.description = description;
        this.ITServices = itServices;
        this.programs = programs;
    }

    public long getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public String getOrder_name() {
        return order_name;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public ArrayList<ITService> getITServices() {
        return ITServices;
    }

    public ArrayList<Program> getPrograms() {
        return programs;
    }
}
