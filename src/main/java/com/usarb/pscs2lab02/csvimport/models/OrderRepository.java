package com.usarb.pscs2lab02.csvimport.models;

import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<Order, Long> {
    public Order findByOrderId(Long orderId);
}
