package com.example.order_service.domain.repository;

import com.example.order_service.domain.model.IdempotencyKeys;

import java.util.Optional;

public interface IdempotencyKeysRepository {

    IdempotencyKeys save(IdempotencyKeys idempotencyKeys);
    Optional<IdempotencyKeys> findByKey(String indempotencyKey);
}
