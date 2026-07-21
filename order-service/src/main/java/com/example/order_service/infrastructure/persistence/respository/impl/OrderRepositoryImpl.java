package com.example.order_service.infrastructure.persistence.respository.impl;

import com.example.order_service.domain.model.Order;
import com.example.order_service.domain.repository.OrderRepository;
import com.example.order_service.infrastructure.persistence.mapper.OrderEntityMapper;
import com.example.order_service.infrastructure.persistence.respository.jpa.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository jpaRepository;
    private final OrderEntityMapper mapper;

    @Override
    public Order save(Order order) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(order)));
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}
