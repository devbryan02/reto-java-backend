package com.example.saga_orchestrator.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record SagaOrderItemRequest(
        @NotNull UUID productId,
        @Positive int quantity
) { }