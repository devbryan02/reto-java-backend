package com.example.saga_orchestrator.infrastructure.client.dispatch.dto;

import java.util.UUID;

public record CreateDispatchClientRequest(
        UUID orderId
) { }