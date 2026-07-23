package com.example.saga_orchestrator.infrastructure.client.order.dto;

public record FailOrderClientRequest(
        String reason
) { }