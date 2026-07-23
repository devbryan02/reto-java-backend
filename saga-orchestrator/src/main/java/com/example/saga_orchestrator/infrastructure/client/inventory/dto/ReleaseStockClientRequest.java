package com.example.saga_orchestrator.infrastructure.client.inventory.dto;

import java.util.UUID;

public record ReleaseStockClientRequest(
        UUID orderId
) { }