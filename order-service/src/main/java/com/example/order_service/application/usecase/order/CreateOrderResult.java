package com.example.order_service.application.usecase.order;

import com.example.order_service.domain.model.Order;

public record CreateOrderResult(
        Order order,
        boolean alreadyExisted
) { }
