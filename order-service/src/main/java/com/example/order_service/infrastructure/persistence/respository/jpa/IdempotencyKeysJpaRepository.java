package com.example.order_service.infrastructure.persistence.respository.jpa;

import com.example.order_service.infrastructure.persistence.entity.IdempotencyKeysEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IdempotencyKeysJpaRepository  extends JpaRepository<IdempotencyKeysEntity, UUID> {

    Optional<IdempotencyKeysEntity> findByIndempotencyKey(String indempotencyKey);

}
