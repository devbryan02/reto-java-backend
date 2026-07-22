package com.example.order_service.infrastructure.rest.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String code,
        String message,
        String traceId
) {}