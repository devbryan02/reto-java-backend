package com.example.saga_orchestrator.domain.exception;

import org.springframework.http.HttpStatus;

public class SagaNotFoundException extends BusinessException {
  public SagaNotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }
}