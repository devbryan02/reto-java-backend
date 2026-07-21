package com.example.order_service.domain.model;

import java.time.Instant;
import java.util.UUID;

public record Product(
        UUID id,
        String name,
        String description,
        double price,
        boolean active,
        Instant createdAt,
        Instant updatedAt
){}
