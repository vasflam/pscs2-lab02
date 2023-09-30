package com.usarb.pscs2lab02.csvimport.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "order_items")
public class OrderItem extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    private OrderPriority priority;

    @ManyToOne
    @JoinColumn(name = "shipping_type_id", referencedColumnName = "id")
    private OrderShippingType shippingType;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column
    private Float discount;

    @Column
    private Integer quantity;

    @Column
    private Float profit;

    @Column
    private Float sales;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderPriority getPriority() {
        return priority;
    }

    public void setPriority(OrderPriority priority) {
        this.priority = priority;
    }

    public OrderShippingType getShippingType() {
        return shippingType;
    }

    public void setShippingType(OrderShippingType shippingType) {
        this.shippingType = shippingType;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getProfit() {
        return profit;
    }

    public void setProfit(Float profit) {
        this.profit = profit;
    }

    public Float getSales() {
        return sales;
    }

    public void setSales(Float sales) {
        this.sales = sales;
    }
}
