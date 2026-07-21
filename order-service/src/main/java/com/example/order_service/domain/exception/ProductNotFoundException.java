package com.example.order_service.domain.exception;

public class ProductNotFoundException extends BussinesException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
