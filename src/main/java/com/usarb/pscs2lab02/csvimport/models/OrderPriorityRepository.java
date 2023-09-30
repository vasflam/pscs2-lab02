package com.usarb.pscs2lab02.csvimport.models;

import org.springframework.data.repository.ListCrudRepository;

public interface OrderPriorityRepository extends ListCrudRepository<OrderPriority, Long> {
    public OrderPriority findByName(String name);
}
