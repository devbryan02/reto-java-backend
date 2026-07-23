package com.example.dispatch_service.domain.model;

import com.example.dispatch_service.domain.enums.DispatchStatus;

import java.time.Instant;
import java.util.UUID;

public record Dispatch(
        UUID id,
        UUID orderId,
        String trackingNumber,
        DispatchStatus status,
        Instant createdAt,
        Instant updatedAt
)
{ }
