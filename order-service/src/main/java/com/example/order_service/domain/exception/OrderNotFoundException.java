package com.example.order_service.domain.exception;

public class OrderNotFoundException extends BussinesException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
