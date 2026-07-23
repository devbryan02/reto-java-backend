package com.example.saga_orchestrator.infrastructure.rest.saga.mapper;

import com.example.saga_orchestrator.application.dto.SagaResponse;
import com.example.saga_orchestrator.application.dto.StartSagaResponse;
import com.example.saga_orchestrator.domain.model.SagaExecution;
import org.springframework.stereotype.Component;

@Component
public class SagaHttpMapper {

    public SagaResponse toSagaResponse(SagaExecution saga) {
        return new SagaResponse(
                saga.id(),
                saga.orderId(),
                saga.status().name(),
                saga.currentStep().name(),
                saga.failureReason(),
                saga.startedAt(),
                saga.finishedAt()
        );
    }

    public StartSagaResponse toStartSagaResponse(SagaExecution saga) {
        return new StartSagaResponse(
                saga.id(),
                saga.orderId(),
                saga.status().name(),
                saga.currentStep().name()
        );
    }

}