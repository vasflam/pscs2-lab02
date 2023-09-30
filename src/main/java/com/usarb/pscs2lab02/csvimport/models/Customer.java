package com.usarb.pscs2lab02.csvimport.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "customers")
public class Customer extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "segment_id", referencedColumnName = "id")
    private CustomerSegment segment;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @Column
    private String name;

    @Column
    private Integer zip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerSegment getSegment() {
        return segment;
    }

    public void setSegment(CustomerSegment segment) {
        this.segment = segment;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }
}
