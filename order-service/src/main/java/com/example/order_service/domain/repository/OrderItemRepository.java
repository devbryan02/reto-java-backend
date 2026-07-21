package com.example.order_service.domain.repository;

import com.example.order_service.domain.model.OrderItem;

public interface OrderItemRepository {

    OrderItem save(OrderItem orderItem);

}
