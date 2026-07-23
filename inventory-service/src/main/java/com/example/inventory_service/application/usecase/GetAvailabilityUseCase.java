package com.example.inventory_service.application.usecase;

import com.example.inventory_service.application.dto.AvailabilityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAvailabilityUseCase {

    private final GetAvailabilityTransactionalExecutor executor;

    public Mono<AvailabilityResponse> execute(UUID productId){
        return Mono.fromCallable(() -> executor.execute(productId))
                .subscribeOn(Schedulers.boundedElastic());
    }

}
