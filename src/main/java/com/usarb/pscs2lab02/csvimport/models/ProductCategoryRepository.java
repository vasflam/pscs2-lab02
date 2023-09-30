package com.usarb.pscs2lab02.csvimport.models;

import org.springframework.data.repository.ListCrudRepository;

public interface ProductCategoryRepository extends ListCrudRepository<ProductCategory, Long> {
    public ProductCategory findByName(String name);
}
