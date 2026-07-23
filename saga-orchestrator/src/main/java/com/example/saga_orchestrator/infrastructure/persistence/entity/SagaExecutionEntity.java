package com.example.saga_orchestrator.infrastructure.persistence.entity;

import com.example.saga_orchestrator.domain.enums.SagaStatus;
import com.example.saga_orchestrator.domain.enums.SagaStep;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Table(name = "saga_execution")
public class SagaExecutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "order_id")
    private UUID orderId;

    @Enumerated(EnumType.STRING)
    private SagaStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_step")
    private SagaStep currentStep;

    @Column(name = "failure_reason")
    private String failureReason;

    @Column(name = "started_at")
    private Instant startedAt;

    @Column(name = "finished_at")
    private Instant finishedAt;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}