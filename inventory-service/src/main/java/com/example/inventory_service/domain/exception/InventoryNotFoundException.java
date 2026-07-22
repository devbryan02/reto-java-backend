package com.example.inventory_service.domain.exception;

import org.springframework.http.HttpStatus;

public class InventoryNotFoundException extends BusinessException {
    public InventoryNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
