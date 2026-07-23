package com.example.saga_orchestrator.infrastructure.client.order.dto;

import java.util.UUID;

public record OrderItemClientRequest(
        UUID productId,
        int quantity
) { }