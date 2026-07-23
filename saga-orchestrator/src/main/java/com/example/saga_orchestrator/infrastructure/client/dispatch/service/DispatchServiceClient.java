package com.example.saga_orchestrator.infrastructure.client.dispatch.service;

import com.example.saga_orchestrator.infrastructure.client.dispatch.dto.CreateDispatchClientRequest;
import com.example.saga_orchestrator.infrastructure.client.dispatch.dto.DispatchClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DispatchServiceClient {

    @Qualifier("dispatchServiceWebClient")
    private final WebClient webClient;

    public Mono<DispatchClientResponse> createDispatch(CreateDispatchClientRequest request, String traceId) {
        return webClient.post()
                .uri("/api/v1/dispatch")
                .header("X-Trace-Id", traceId)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(DispatchClientResponse.class);
    }

}