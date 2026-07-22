package com.example.inventory_service.domain.model;

import java.time.Instant;
import java.util.UUID;

public record Inventory(
        UUID productId,
        Integer availableStock,
        Integer reservedStock,
        Instant updatedAt
)
{ }
