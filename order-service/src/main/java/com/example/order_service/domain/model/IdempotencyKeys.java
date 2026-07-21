package com.example.order_service.domain.model;

import java.time.Instant;
import java.util.UUID;

public record IdempotencyKeys(
        UUID id,
        String indempotencyKey,
        Order order,
        Instant createdAt
) { }
