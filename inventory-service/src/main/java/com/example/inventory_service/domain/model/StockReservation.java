package com.example.inventory_service.domain.model;

import java.time.Instant;
import java.util.UUID;

public record StockReservation(
        UUID id,
        UUID orderId,
        UUID productId,
        Integer quantity,
        String status,
        Instant createdAt
) { }
