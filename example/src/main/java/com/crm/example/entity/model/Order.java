package com.crm.example.entity.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "order")
public class Order {
    @Id
    private int id;
    @Column(value = "order_name")
    private String order_name;
    @Column(value = "priority")
    private long priority;
    @Column(value = "status_id")
    private long status_id;
    @Column(value = "description")
    private String description;
    @Column(value = "comments")
    private String comments;
    @Column(value = "client_id")
    private long client_id;
    @Column(value = "user_id")
    private long user_id;

    public int getId() {
        return id;
    }

    public String getOrder_name() {
        return order_name;
    }

    public long getPriority() {
        return priority;
    }

    public long getStatus_id() {
        return status_id;
    }

    public String getDescription() {
        return description;
    }

    public String getComments() {
        return comments;
    }

    public long getClient_id() {
        return client_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    public void setStatus_id(long status_id) {
        this.status_id = status_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
