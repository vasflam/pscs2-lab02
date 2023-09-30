package com.usarb.pscs2lab02.csvimport.models;

import org.springframework.data.repository.ListCrudRepository;

public interface ProductBoxRepository extends ListCrudRepository<ProductBox, Long> {
    public ProductBox findByName(String name):
}
