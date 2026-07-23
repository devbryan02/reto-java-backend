package com.example.dispatch_service.application.usecase;

import com.example.dispatch_service.application.dto.CreateDispacthRequest;
import com.example.dispatch_service.domain.model.Dispatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class CreateDispatchUseCase {

    private final CreateDispatchTransactionExecutor executor;

    public Mono<Dispatch> execute(CreateDispacthRequest request){
        return Mono.fromCallable(() -> executor.execute(request))
                .subscribeOn(Schedulers.boundedElastic());
    }

}
