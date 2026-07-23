package com.example.saga_orchestrator.infrastructure.persistence.repository.impl;

import com.example.saga_orchestrator.domain.model.SagaExecution;
import com.example.saga_orchestrator.domain.repository.SagaExecutionRepository;
import com.example.saga_orchestrator.infrastructure.persistence.mapper.SagaExecutionEntityMapper;
import com.example.saga_orchestrator.infrastructure.persistence.repository.jpa.SagaExecutionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SagaExecutionRepositoryImpl implements SagaExecutionRepository {

    private final SagaExecutionJpaRepository jpaRepository;
    private final SagaExecutionEntityMapper mapper;

    @Override
    public SagaExecution save(SagaExecution sagaExecution) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(sagaExecution)));
    }

    @Override
    public Optional<SagaExecution> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<SagaExecution> findByOrderId(UUID orderId) {
        return jpaRepository.findByOrderId(orderId).map(mapper::toDomain);
    }
}