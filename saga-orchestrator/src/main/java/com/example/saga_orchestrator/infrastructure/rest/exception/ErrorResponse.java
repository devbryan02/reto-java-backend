package com.example.saga_orchestrator.infrastructure.rest.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String code,
        String message,
        String traceId
) {}