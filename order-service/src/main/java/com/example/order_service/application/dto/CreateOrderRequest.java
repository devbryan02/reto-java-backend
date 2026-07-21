package com.example.order_service.application.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
        @NotNull UUID userId,
        @NotNull @Valid List<OrderItemRequest> items
) { }
