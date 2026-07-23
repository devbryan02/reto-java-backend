package com.example.dispatch_service.domain.exception;

import org.springframework.http.HttpStatus;

public class DuplicatedDispatchStatus extends BusinessException {
    public DuplicatedDispatchStatus(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
