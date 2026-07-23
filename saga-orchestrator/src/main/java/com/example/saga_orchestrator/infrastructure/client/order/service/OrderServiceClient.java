package com.example.saga_orchestrator.infrastructure.client.order.service;

import com.example.saga_orchestrator.infrastructure.client.order.dto.CreateOrderClientRequest;
import com.example.saga_orchestrator.infrastructure.client.order.dto.CreateOrderClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrderServiceClient {

    @Qualifier("orderServiceWebClient")
    private final WebClient webClient;

    public Mono<CreateOrderClientResponse> createOrder(CreateOrderClientRequest request, String traceId) {
        return webClient.post()
                .uri("/api/v1/orders")
                .header("X-Trace-Id", traceId)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(CreateOrderClientResponse.class);
    }

}