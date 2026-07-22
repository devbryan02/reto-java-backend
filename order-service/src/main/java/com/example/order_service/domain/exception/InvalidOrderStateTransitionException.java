package com.example.order_service.domain.exception;

public class InvalidOrderStateTransitionException extends BussinesException {
    public InvalidOrderStateTransitionException(String message) {
        super(message);
    }
}
