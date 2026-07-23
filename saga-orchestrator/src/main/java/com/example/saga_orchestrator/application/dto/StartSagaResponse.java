package com.example.saga_orchestrator.application.dto;

import java.util.UUID;

public record StartSagaResponse(
        UUID sagaId,
        UUID orderId,
        String sagaStatus,
        String currentStep
) { }