package com.example.saga_orchestrator.infrastructure.persistence.repository.jpa;

import com.example.saga_orchestrator.infrastructure.persistence.entity.SagaExecutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SagaExecutionJpaRepository extends JpaRepository<SagaExecutionEntity, UUID> {

    Optional<SagaExecutionEntity> findByOrderId(UUID orderId);

}