package com.usarb.pscs2lab02.csvimport.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "orders")
public class Order extends BaseModel {
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    private OrderPriority priority;

    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime shippedAt;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderPriority getPriority() {
        return priority;
    }

    public void setPriority(OrderPriority priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getShippedAt() {
        return shippedAt;
    }

    public void setShippedAt(LocalDateTime shippedAt) {
        this.shippedAt = shippedAt;
    }
}
