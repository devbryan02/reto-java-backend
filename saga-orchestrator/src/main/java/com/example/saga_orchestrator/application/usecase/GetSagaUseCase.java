package com.example.saga_orchestrator.application.usecase;

import com.example.saga_orchestrator.domain.exception.SagaNotFoundException;
import com.example.saga_orchestrator.domain.model.SagaExecution;
import com.example.saga_orchestrator.domain.repository.SagaExecutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetSagaUseCase {

    private final SagaExecutionRepository sagaExecutionRepository;

    public Mono<SagaExecution> execute(UUID sagaId) {
        return Mono.fromCallable(() ->
                sagaExecutionRepository.findById(sagaId)
                        .orElseThrow(() -> new SagaNotFoundException("Saga no encontrada con id: " + sagaId))
        ).subscribeOn(Schedulers.boundedElastic());
    }

}