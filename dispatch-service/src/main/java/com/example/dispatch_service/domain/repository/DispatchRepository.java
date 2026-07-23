package com.example.dispatch_service.domain.repository;

import com.example.dispatch_service.domain.model.Dispatch;

import java.util.Optional;
import java.util.UUID;

public interface DispatchRepository {

    Dispatch save(Dispatch dispatch);
    Optional<Dispatch> findByOrderId(UUID orderId);

}
