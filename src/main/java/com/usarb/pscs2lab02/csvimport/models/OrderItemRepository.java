package com.usarb.pscs2lab02.csvimport.models;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface OrderItemRepository extends ListCrudRepository<OrderItem, Long> {
    public List<OrderItem> findByOrderId(Long orderId);
    public OrderItem findByOrderIdAndProductId(Long orderId, Long productId);
}
