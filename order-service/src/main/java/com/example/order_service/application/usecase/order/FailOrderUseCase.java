package com.example.order_service.application.usecase.order;

import com.example.order_service.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FailOrderUseCase {

    private final FailOrderTransactionalExecutor executor;

    public Mono<Order> execute(UUID orderId, String reason) {
        return Mono.fromCallable(() -> executor.execute(orderId, reason))
                .subscribeOn(Schedulers.boundedElastic());
    }

}