package com.example.order_service.domain.exception;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends BusinessException {
    public OrderNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
