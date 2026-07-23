package com.example.saga_orchestrator.infrastructure.client.order.dto;

import java.util.List;
import java.util.UUID;

public record CreateOrderClientRequest(
        UUID userId,
        List<OrderItemClientRequest> items
) { }