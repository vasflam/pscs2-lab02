package com.usarb.pscs2lab02.csvimport.models;

import org.springframework.data.repository.ListCrudRepository;

public interface CityRepository extends ListCrudRepository<City, Long> {
    public City findByName(String name);
}
