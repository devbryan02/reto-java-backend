package com.example.order_service.domain.repository;

import com.example.order_service.domain.model.IndempotencyKeys;

public interface IndempotencyKeysRepository {

    IndempotencyKeys save(IndempotencyKeys indempotencyKeys);


}
