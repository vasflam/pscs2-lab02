package com.usarb.pscs2lab02.csvimport.models;

import jakarta.persistence.*;

@Entity(name = "cities")
public class City extends BaseModel {
    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private State state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
