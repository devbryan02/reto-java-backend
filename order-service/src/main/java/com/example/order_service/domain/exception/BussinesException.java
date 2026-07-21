package com.example.order_service.domain.exception;

public class BussinesException extends RuntimeException {
    public BussinesException(String message) {
        super(message);
    }
}
