package com.example.saga_orchestrator.infrastructure.client.inventory.dto;

import java.util.List;
import java.util.UUID;

public record ReserveStockClientRequest(
        UUID orderId,
        List<StockItemClientRequest> items
) { }