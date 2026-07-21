package com.example.order_service.domain.model;

import com.example.order_service.domain.enums.OrderStatus;

import java.time.Instant;
import java.util.UUID;

public record OrderHistory(
        UUID id,
        Order order,
        OrderStatus status,
        String message,
        Instant createdAt
) { }
