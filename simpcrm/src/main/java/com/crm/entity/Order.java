package com.crm.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "order")
public class Order {
    @Id
    private Long id;
    @Column(value = "order_name")
    private String orderName;
    @Column(value = "priority")
    private Long priority;
    @Column(value = "status_id")
    private Long status_id;
    @Column(value = "description")
    private String description;
    @Column(value = "comments")
    private String comments;
    @Column(value = "client_id")
    private Long client_id;
    @Column(value = "user_id")
    private Long user_id;

    public Long getId() {
        return id;
    }

    public String getOrder_name() {
        return orderName;
    }

    public Long getPriority() {
        return priority;
    }

    public Long getStatus_id() {
        return status_id;
    }

    public String getDescription() {
        return description;
    }

    public String getComments() {
        return comments;
    }

    public Long getClient_id() {
        return client_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder_name(String order_name) {
        this.orderName = order_name;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
