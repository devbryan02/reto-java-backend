package com.example.inventory_service.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record StockItemRequest(
        @NotNull UUID productId,
        @Positive int quantity
) { }
