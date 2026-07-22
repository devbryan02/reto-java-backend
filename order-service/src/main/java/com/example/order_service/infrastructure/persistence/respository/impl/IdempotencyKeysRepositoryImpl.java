package com.example.order_service.infrastructure.persistence.respository.impl;

import com.example.order_service.domain.model.IdempotencyKeys;
import com.example.order_service.domain.repository.IdempotencyKeysRepository;
import com.example.order_service.infrastructure.persistence.mapper.IdepotencyKeysEntityMapper;
import com.example.order_service.infrastructure.persistence.respository.jpa.IdempotencyKeysJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class IdempotencyKeysRepositoryImpl implements IdempotencyKeysRepository {

    private final IdempotencyKeysJpaRepository jpaRepository;
    private final IdepotencyKeysEntityMapper mapper;

    @Override
    public IdempotencyKeys save(IdempotencyKeys idempotencyKeys) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(idempotencyKeys)));
    }

    @Override
    public Optional<IdempotencyKeys> findByKey(String indempotencyKey) {
        return jpaRepository.findByIdempotencyKey(indempotencyKey)
                .map(mapper::toDomain);
    }
}
