package com.example.saga_orchestrator.domain.enums;

public enum SagaStatus {
    STARTED,
    COMPLETED,
    COMPENSATING,
    COMPENSATED,
    FAILED
}