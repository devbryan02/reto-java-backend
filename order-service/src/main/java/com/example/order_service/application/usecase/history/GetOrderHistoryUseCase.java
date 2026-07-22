package com.example.order_service.application.usecase.history;

import com.example.order_service.domain.model.OrderHistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GetOrderHistoryUseCase {

    private final GetOrderHistoryTransactionalExecutor executor;

    public Flux<OrderHistory> execute(UUID orderId){
        return Flux.fromIterable(executor.execute(orderId))
                .subscribeOn(Schedulers.boundedElastic());
    }

}
