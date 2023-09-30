package com.usarb.pscs2lab02.csvimport.models;

import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
public class BaseModel implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private boolean isNew;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
