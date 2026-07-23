package com.example.saga_orchestrator.infrastructure.client.order.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderClientResponse(
        UUID id,
        UUID userId,
        String status,
        BigDecimal totalAmount,
        Instant createdAt,
        Instant updatedAt
) { }