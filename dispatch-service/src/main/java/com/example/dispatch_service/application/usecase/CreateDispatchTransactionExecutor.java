package com.example.dispatch_service.application.usecase;

import com.example.dispatch_service.application.dto.CreateDispacthRequest;
import com.example.dispatch_service.domain.model.Dispatch;
import com.example.dispatch_service.domain.repository.DispatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateDispatchTransactionExecutor {

    private final DispatchRepository dispatchRepository;

    @Transactional
    public Dispatch execute(CreateDispacthRequest request){

        // 1. Evitar crear el mismo dispatch para el mismo pedido
        Optional<Dispatch> existingDispatch = dispatchRepository.findByOrderId(request.orderId());
        if(existingDispatch.isPresent()){
            log.info("Dispatch para el pedido {} ya existe", request.orderId());
            return  existingDispatch.get();
        }

        // 2. crear el despacho
        Dispatch dispatch = Dispatch.createDispatch(request.orderId());
        Dispatch dispatchSaved = dispatchRepository.save(dispatch);
        log.info("Dispatch creado con id: {}", dispatchSaved.id());

        return dispatchSaved;
    }

}
