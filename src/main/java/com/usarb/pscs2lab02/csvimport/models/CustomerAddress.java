package com.usarb.pscs2lab02.csvimport.models;

import jakarta.persistence.*;

@Entity(name = "addresses")
public class CustomerAddress extends BaseModel {
    @OneToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @Column
    private Integer postalCode;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }
}
