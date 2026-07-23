package com.example.saga_orchestrator.domain.repository;

import com.example.saga_orchestrator.domain.model.SagaExecution;

import java.util.Optional;
import java.util.UUID;

public interface SagaExecutionRepository {

    SagaExecution save(SagaExecution sagaExecution);
    Optional<SagaExecution> findById(UUID id);
    Optional<SagaExecution> findByOrderId(UUID orderId);

}