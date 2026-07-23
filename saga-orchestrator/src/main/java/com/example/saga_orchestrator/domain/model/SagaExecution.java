package com.example.saga_orchestrator.domain.model;

import com.example.saga_orchestrator.domain.enums.SagaStatus;
import com.example.saga_orchestrator.domain.enums.SagaStep;

import java.time.Instant;
import java.util.UUID;

public record SagaExecution(
        UUID id,
        UUID orderId,
        SagaStatus status,
        SagaStep currentStep,
        String failureReason,
        Instant startedAt,
        Instant finishedAt,
        Instant createdAt,
        Instant updatedAt
) {

    public static SagaExecution start(UUID orderId) {
        Instant now = Instant.now();
        return new SagaExecution(
                null,
                orderId,
                SagaStatus.STARTED,
                SagaStep.ORDER_CREATED,
                null,
                now,
                null,
                now,
                now
        );
    }

    public SagaExecution advanceTo(SagaStep step) {
        return new SagaExecution(
                this.id, this.orderId,
                this.status,
                step,
                this.failureReason,
                this.startedAt,
                this.finishedAt,
                this.createdAt,
                Instant.now()
        );
    }

    public SagaExecution complete() {
        return new SagaExecution(
                this.id,
                this.orderId,
                SagaStatus.COMPLETED,
                this.currentStep,
                this.failureReason,
                this.startedAt,
                Instant.now(),
                this.createdAt,
                Instant.now()
        );
    }

    public SagaExecution startCompensation(String reason) {
        return new SagaExecution(
                this.id,
                this.orderId,
                SagaStatus.COMPENSATING,
                this.currentStep,
                reason,
                this.startedAt,
                this.finishedAt,
                this.createdAt,
                Instant.now()
        );
    }

    public SagaExecution compensated() {
        return new SagaExecution(
                this.id,
                this.orderId,
                SagaStatus.COMPENSATED,
                this.currentStep,
                this.failureReason,
                this.startedAt,
                Instant.now(),
                this.createdAt,
                Instant.now()
        );
    }

    public SagaExecution fail(String reason) {
        return new SagaExecution(
                this.id,
                this.orderId,
                SagaStatus.FAILED,
                this.currentStep,
                reason,
                this.startedAt,
                Instant.now(),
                this.createdAt,
                Instant.now()
        );
    }
}