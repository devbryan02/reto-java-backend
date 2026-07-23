package com.example.inventory_service.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record ReserveStockRequest(
        @NotNull UUID orderId,
        @NotNull @NotEmpty @Valid List<StockItemRequest> items
) { }
