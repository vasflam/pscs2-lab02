package com.usarb.pscs2lab02.csvimport.models;

import org.springframework.data.repository.ListCrudRepository;

public interface StateRepository extends ListCrudRepository<State, Long> {
    public State findByName(String name);
}
