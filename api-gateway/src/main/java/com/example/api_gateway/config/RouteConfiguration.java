package com.example.api_gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouteLocator routes(
            RouteLocatorBuilder builder,
            @Value("${services.order-service.url}") String orderServiceUrl,
            @Value("${services.inventory-service.url}") String inventoryServiceUrl,
            @Value("${services.saga-orchestrator.url}") String sagaOrchestratorUrl) {

        return builder.routes()

                // POST /api/v1/orders -> se traduce a POST /api/v1/sagas en Saga Orchestrator
                .route("create-order-via-saga", r -> r
                        .method(HttpMethod.POST)
                        .and()
                        .path("/api/v1/orders")
                        .filters(f -> f.rewritePath("/api/v1/orders", "/api/v1/sagas"))
                        .uri(sagaOrchestratorUrl))

                // GET /orders/{id}, /orders/{id}/cancel, /orders/{id}/history -> directo a Order Service
                .route("order-service-direct", r -> r
                        .path("/api/v1/orders/**")
                        .uri(orderServiceUrl))

                // GET /api/v1/sagas/{id} -> directo a Saga Orchestrator
                .route("saga-direct", r -> r
                        .path("/api/v1/sagas/**")
                        .uri(sagaOrchestratorUrl))

                // GET /api/v1/inventory/availability -> directo a Inventory Service
                .route("inventory-direct", r -> r
                        .method(HttpMethod.GET)
                        .and().path("/api/v1/inventory/availability")
                        .uri(inventoryServiceUrl))

                .build();
    }
}