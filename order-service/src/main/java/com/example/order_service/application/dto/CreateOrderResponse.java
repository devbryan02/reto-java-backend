package com.example.order_service.application.dto;

public record CreateOrderResponse(
        OrderResponse order,
        boolean alreadyExisted
) { }
