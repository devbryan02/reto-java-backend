package com.example.order_service.infrastructure.persistence.respository.impl;

import com.example.order_service.domain.model.Order;
import com.example.order_service.domain.repository.OrderRespository;
import com.example.order_service.infrastructure.persistence.mapper.OrderEntityMapper;
import com.example.order_service.infrastructure.persistence.respository.jpa.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRespository {

    private final OrderJpaRepository jpaRepository;
    private final OrderEntityMapper mapper;

    @Override
    public Order save(Order order) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(order)));
    }
}
