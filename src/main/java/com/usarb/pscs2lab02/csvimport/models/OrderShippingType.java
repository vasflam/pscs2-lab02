package com.usarb.pscs2lab02.csvimport.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "order_shipping_types")
public class OrderShippingType extends BaseModel {
    @Column(unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
