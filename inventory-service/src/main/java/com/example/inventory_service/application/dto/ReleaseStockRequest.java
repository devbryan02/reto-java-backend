package com.example.inventory_service.application.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReleaseStockRequest(
        @NotNull UUID orderId
) { }
