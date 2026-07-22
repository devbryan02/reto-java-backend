package com.example.order_service.domain.model;

import java.time.Instant;
import java.util.UUID;

public record IdempotencyKeys(
        UUID id,
        String idempotencyKey,
        Order order,
        Instant createdAt
) {

    public static IdempotencyKeys createIdempotencyKeys(String idempotencyKey, Order order) {
        return new IdempotencyKeys(null, idempotencyKey, order, Instant.now());
    }

}
