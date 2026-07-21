package com.example.order_service.domain.repository;

import com.example.order_service.domain.model.Order;

public interface OrderRespository {

    Order save(Order order);

}
