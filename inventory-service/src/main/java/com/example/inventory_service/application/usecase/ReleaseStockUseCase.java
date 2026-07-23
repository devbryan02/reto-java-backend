package com.example.inventory_service.application.usecase;

import com.example.inventory_service.application.dto.ReleaseStockRequest;
import com.example.inventory_service.application.dto.ReleaseStockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ReleaseStockUseCase {

    private final ReleaseStockTransactionalExecutor executor;

    public Mono<ReleaseStockResponse> execute(ReleaseStockRequest request){
        return Mono.fromCallable(() -> executor.execute(request))
                .subscribeOn(Schedulers.boundedElastic());
    }

}
