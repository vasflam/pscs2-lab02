package com.usarb.pscs2lab02.csvimport.models;

import org.springframework.data.repository.ListCrudRepository;

public interface OrderItemRepository extends ListCrudRepository<OrderItem, Long> {
}
