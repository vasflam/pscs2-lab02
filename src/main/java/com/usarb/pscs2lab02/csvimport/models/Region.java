package com.usarb.pscs2lab02.csvimport.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "regions")
public class Region extends BaseModel {
    @Column(unique = true)
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
