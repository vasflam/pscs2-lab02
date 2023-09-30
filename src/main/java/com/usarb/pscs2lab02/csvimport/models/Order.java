package com.usarb.pscs2lab02.csvimport.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "orders")
public class Order extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    private OrderPriority priority;

    @Temporal(value = TemporalType.DATE)
    private LocalDate createdAt;

    @Temporal(value = TemporalType.DATE)
    private LocalDate shippedAt;

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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getShippedAt() {
        return shippedAt;
    }

    public void setShippedAt(LocalDate shippedAt) {
        this.shippedAt = shippedAt;
    }
}
