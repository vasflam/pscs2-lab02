package com.usarb.pscs2lab02.csvimport.models;

import jakarta.persistence.*;

@Entity(name = "states")
public class State extends BaseModel {
    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private Region region;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
