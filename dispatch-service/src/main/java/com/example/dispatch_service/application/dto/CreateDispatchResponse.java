package com.example.dispatch_service.application.dto;

import com.example.dispatch_service.domain.enums.DispatchStatus;

import java.time.Instant;
import java.util.UUID;

public record CreateDispatchResponse(
        UUID id,
        UUID orderId,
        String trackingNumber,
        DispatchStatus status,
        Instant createdAt
) { }