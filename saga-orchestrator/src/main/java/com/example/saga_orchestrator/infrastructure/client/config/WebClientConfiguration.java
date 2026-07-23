package com.example.saga_orchestrator.infrastructure.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean(name = "orderServiceWebClient")
    public WebClient orderServiceWebClient(
            @Value("${services.order-service.url}") String baseUrl) {
        return WebClient.builder().baseUrl(baseUrl).build();
    }

    @Bean(name = "inventoryServiceWebClient")
    public WebClient inventoryServiceWebClient(
            @Value("${services.inventory-service.url}") String baseUrl) {
        return WebClient.builder().baseUrl(baseUrl).build();
    }

    @Bean(name = "dispatchServiceWebClient")
    public WebClient dispatchServiceWebClient(
            @Value("${services.dispatch-service.url}") String baseUrl) {
        return WebClient.builder().baseUrl(baseUrl).build();
    }

}