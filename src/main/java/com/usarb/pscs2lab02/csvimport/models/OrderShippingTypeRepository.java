package com.usarb.pscs2lab02.csvimport.models;

import org.springframework.data.repository.ListCrudRepository;

public interface OrderShippingTypeRepository extends ListCrudRepository<OrderShippingType, Long> {
    public OrderShippingType findByName(String name);
}
