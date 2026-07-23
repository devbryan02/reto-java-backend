package com.example.saga_orchestrator.infrastructure.client.order.dto;

public record CreateOrderClientResponse(
        OrderClientResponse order,
        boolean alreadyExisted
) { }