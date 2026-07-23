package com.example.saga_orchestrator.application.dto;

import java.time.Instant;
import java.util.UUID;

public record SagaResponse(
        UUID id,
        UUID orderId,
        String status,
        String currentStep,
        String failureReason,
        Instant startedAt,
        Instant finishedAt
) { }