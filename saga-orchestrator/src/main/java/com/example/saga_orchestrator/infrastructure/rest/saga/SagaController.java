package com.example.saga_orchestrator.infrastructure.rest.saga;

import com.example.saga_orchestrator.application.dto.SagaResponse;
import com.example.saga_orchestrator.application.dto.StartSagaRequest;
import com.example.saga_orchestrator.application.dto.StartSagaResponse;
import com.example.saga_orchestrator.application.usecase.GetSagaUseCase;
import com.example.saga_orchestrator.application.usecase.StartSagaUseCase;
import com.example.saga_orchestrator.infrastructure.rest.saga.mapper.SagaHttpMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sagas")
@RequiredArgsConstructor
@Tag(name = "Saga", description = "Saga Orchestrator API")
public class SagaController {

    private final StartSagaUseCase startSagaUseCase;
    private final GetSagaUseCase getSagaUseCase;
    private final SagaHttpMapper httpMapper;

    @PostMapping
    @Operation(summary = "Start a new saga (create order, reserve stock, create dispatch)")
    public Mono<ResponseEntity<StartSagaResponse>> startSaga(
            @RequestBody @Valid StartSagaRequest request,
            ServerWebExchange exchange) {

        String traceId = getOrGenerateTraceId(exchange);

        return startSagaUseCase.execute(request, traceId)
                .map(saga -> ResponseEntity.status(HttpStatus.CREATED)
                        .header("X-Trace-Id", traceId)
                        .body(httpMapper.toStartSagaResponse(saga)));
    }

    @GetMapping("/{sagaId}")
    @Operation(summary = "Get saga execution status by id")
    public Mono<ResponseEntity<SagaResponse>> getSaga(@PathVariable("sagaId") UUID sagaId) {
        return getSagaUseCase.execute(sagaId)
                .map(saga -> ResponseEntity.ok(httpMapper.toSagaResponse(saga)));
    }

    private String getOrGenerateTraceId(ServerWebExchange exchange) {
        String traceId = exchange.getRequest().getHeaders().getFirst("X-Trace-Id");
        return (traceId != null && !traceId.isBlank()) ? traceId : UUID.randomUUID().toString();
    }
}