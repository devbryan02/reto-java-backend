package com.example.inventory_service.application.dto;

import java.util.UUID;

public record AvailabilityResponse(
        UUID productId,
        int availableStock
) { }
