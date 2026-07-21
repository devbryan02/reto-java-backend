package com.example.order_service.application.usecase.order;

import com.example.order_service.application.dto.CreateOrderRequest;
import reactor.core.publisher.Mono;

public interface CreateOrderUseCase {

    Mono<CreateOrderResult> execute(CreateOrderRequest request, String idempotencyKey);

}
