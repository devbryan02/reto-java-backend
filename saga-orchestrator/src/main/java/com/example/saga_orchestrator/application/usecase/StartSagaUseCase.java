package com.example.saga_orchestrator.application.usecase;

import com.example.saga_orchestrator.application.dto.StartSagaRequest;
import com.example.saga_orchestrator.domain.model.SagaExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class StartSagaUseCase {

    private final StartSagaTransactionalExecutor executor;

    public Mono<SagaExecution> execute(StartSagaRequest request, String traceId) {
        return Mono.fromCallable(() -> executor.execute(request, traceId))
                .subscribeOn(Schedulers.boundedElastic());
    }

}