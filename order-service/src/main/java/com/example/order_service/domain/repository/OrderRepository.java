package com.example.order_service.domain.repository;

import com.example.order_service.domain.model.Order;

public interface OrderRepository {

    Order save(Order order);

}
