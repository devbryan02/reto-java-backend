package com.example.saga_orchestrator.infrastructure.client.inventory.service;

import com.example.saga_orchestrator.infrastructure.client.inventory.dto.ReleaseStockClientRequest;
import com.example.saga_orchestrator.infrastructure.client.inventory.dto.ReserveStockClientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class InventoryServiceClient {

    @Qualifier("inventoryServiceWebClient")
    private final WebClient webClient;

    public Mono<Void> reserveStock(ReserveStockClientRequest request, String traceId) {
        return webClient.post()
                .uri("/api/v1/inventory/reservations")
                .header("X-Trace-Id", traceId)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Void> releaseStock(ReleaseStockClientRequest request, String traceId) {
        return webClient.post()
                .uri("/api/v1/inventory/reservations/release")
                .header("X-Trace-Id", traceId)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class);
    }

}