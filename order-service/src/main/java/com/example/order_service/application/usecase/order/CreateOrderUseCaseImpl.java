package com.example.order_service.application.usecase.order;

import com.example.order_service.application.dto.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final CreateOrderTransactionalExecutor executor;

    @Override
    public Mono<CreateOrderResult> execute(CreateOrderRequest request, String idempotencyKey) {
        return Mono.fromCallable(() -> executor.execute(request, idempotencyKey))
                .subscribeOn(Schedulers.boundedElastic());
    }
}