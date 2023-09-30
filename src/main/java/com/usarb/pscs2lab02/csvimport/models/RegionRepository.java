package com.usarb.pscs2lab02.csvimport.models;

import org.springframework.data.repository.ListCrudRepository;

public interface RegionRepository extends ListCrudRepository<Region, Long> {
    public Region findByName(String name);
}
