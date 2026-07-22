package com.example.order_service.domain.exception;

import org.springframework.http.HttpStatus;

public class InvalidOrderStateTransitionException extends BusinessException {
    public InvalidOrderStateTransitionException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
