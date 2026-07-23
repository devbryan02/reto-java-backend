package com.example.inventory_service.application.dto;

import java.util.UUID;

public record ReleaseStockResponse(
        UUID orderId,
        int releasedItems
) { }
