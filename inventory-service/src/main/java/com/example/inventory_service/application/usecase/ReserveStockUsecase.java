package com.example.inventory_service.application.usecase;

import com.example.inventory_service.application.dto.ReserveStockRequest;
import com.example.inventory_service.application.dto.ReserveStockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ReserveStockUsecase {

    private final ReserveStockTransactionalExecutor executor;

    public Mono<ReserveStockResponse> execute(ReserveStockRequest request) {
        return Mono.fromCallable(() -> executor.execute(request))
                .subscribeOn(Schedulers.boundedElastic());
    }

}
