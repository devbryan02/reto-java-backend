package com.example.inventory_service.application.dto;

import java.util.UUID;

public record ReservationItemResponse(
        UUID productId,
        int quantity
) { }
