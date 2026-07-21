package com.example.order_service.domain.repository;

import com.example.order_service.domain.model.IdempotencyKeys;

public interface IdempotencyKeysRepository {

    IdempotencyKeys save(IdempotencyKeys idempotencyKeys);


}
