package com.example.inventory_service.application.dto;

import java.util.List;
import java.util.UUID;

public record ReserveStockResponse(
        UUID orderId,
        List<ReservationItemResponse> items
) { }
