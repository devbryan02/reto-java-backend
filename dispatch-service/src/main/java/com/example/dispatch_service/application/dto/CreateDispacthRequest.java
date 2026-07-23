package com.example.dispatch_service.application.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateDispacthRequest(
        @NotNull UUID orderId
) { }
