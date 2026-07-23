package com.example.saga_orchestrator.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record StartSagaRequest(
        @NotNull UUID userId,
        @NotNull @Valid List<SagaOrderItemRequest> items
) { }