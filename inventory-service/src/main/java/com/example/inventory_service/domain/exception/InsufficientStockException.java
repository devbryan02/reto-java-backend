package com.example.inventory_service.domain.exception;

import org.springframework.http.HttpStatus;

public class InsufficientStockException extends BusinessException {
    public InsufficientStockException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
