package com.example.saga_orchestrator.infrastructure.client.dispatch.dto;

import java.time.Instant;
import java.util.UUID;

public record DispatchClientResponse(
        UUID id,
        UUID orderId,
        String trackingNumber,
        String status,
        Instant createdAt
) { }