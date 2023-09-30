package com.usarb.pscs2lab02.csvimport.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "order_items")
public class OrderItem extends BaseModel {
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    private OrderPriority priority;

    @OneToOne
    @JoinColumn(name = "shipping_type_id", referencedColumnName = "id")
    private OrderShippingType shipping;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column
    private Float price;

    @Column
    private Float discount;

    @Column
    private Float shippingCost;

    @Column
    private Integer quantity;

    @Column
    private Float profit;

    @Column
    private Float sales;
}
