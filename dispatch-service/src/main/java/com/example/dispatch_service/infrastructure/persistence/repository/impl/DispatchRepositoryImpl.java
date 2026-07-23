package com.example.dispatch_service.infrastructure.persistence.repository.impl;

import com.example.dispatch_service.domain.model.Dispatch;
import com.example.dispatch_service.domain.repository.DispatchRepository;
import com.example.dispatch_service.infrastructure.persistence.mapper.DispatchEntityMapper;
import com.example.dispatch_service.infrastructure.persistence.repository.jpa.DispatchJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DispatchRepositoryImpl implements DispatchRepository {

    private final DispatchJpaRepository jpaRepository;
    private final DispatchEntityMapper mapper;

    @Override
    public Dispatch save(Dispatch dispatch) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(dispatch)));
    }

    @Override
    public Optional<Dispatch> findByOrderId(UUID orderId) {
        return jpaRepository.findByOrderId(orderId).map(mapper::toDomain);
    }
}
