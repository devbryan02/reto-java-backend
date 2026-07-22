package com.example.order_service.application.dto;

import com.example.order_service.domain.enums.OrderStatus;

import java.time.Instant;
import java.util.UUID;

public record OrderHistoryResponse(
        UUID id,
        OrderStatus status,
        String message,
        Instant createdAt
) { }
