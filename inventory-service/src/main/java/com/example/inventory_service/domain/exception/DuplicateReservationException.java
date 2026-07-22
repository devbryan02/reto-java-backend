package com.example.inventory_service.domain.exception;

import org.springframework.http.HttpStatus;

public class DuplicateReservationException extends BusinessException {
    public DuplicateReservationException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
