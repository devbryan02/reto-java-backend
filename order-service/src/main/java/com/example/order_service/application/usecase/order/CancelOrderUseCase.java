package com.example.order_service.application.usecase.order;

import com.example.order_service.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CancelOrderUseCase {

    private final CancelOrderTransactionalExecutor executor;

    public Mono<Order> execute(UUID orderId) {
        return Mono.fromCallable(() -> executor.execute(orderId))
                .subscribeOn(Schedulers.boundedElastic());
    }

}
