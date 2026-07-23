package com.example.saga_orchestrator.infrastructure.client.order;

import com.example.saga_orchestrator.infrastructure.client.order.dto.CreateOrderClientRequest;
import com.example.saga_orchestrator.infrastructure.client.order.dto.CreateOrderClientResponse;
import com.example.saga_orchestrator.infrastructure.client.order.dto.FailOrderClientRequest;
import com.example.saga_orchestrator.infrastructure.client.order.dto.OrderClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

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

    public Mono<OrderClientResponse> confirmOrder(UUID orderId, String traceId) {
        return webClient.post()
                .uri("/api/v1/orders/{orderId}/confirm", orderId)
                .header("X-Trace-Id", traceId)
                .retrieve()
                .bodyToMono(OrderClientResponse.class);
    }

    public Mono<OrderClientResponse> failOrder(UUID orderId, String reason, String traceId) {
        return webClient.post()
                .uri("/api/v1/orders/{orderId}/fail", orderId)
                .header("X-Trace-Id", traceId)
                .bodyValue(new FailOrderClientRequest(reason))
                .retrieve()
                .bodyToMono(OrderClientResponse.class);
    }

}