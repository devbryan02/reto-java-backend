package com.example.dispatch_service.domain.exception;

import org.springframework.http.HttpStatus;

public class DispatchNotFoundException extends BusinessException {
    public DispatchNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
