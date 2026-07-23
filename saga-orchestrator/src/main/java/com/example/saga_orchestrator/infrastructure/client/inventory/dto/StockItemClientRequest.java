package com.example.saga_orchestrator.infrastructure.client.inventory.dto;

import java.util.UUID;

public record StockItemClientRequest(
        UUID productId,
        int quantity
) { }