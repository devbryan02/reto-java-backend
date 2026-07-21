package com.example.order_service.infrastructure.persistence.respository.impl;

import com.example.order_service.domain.model.OrderItem;
import com.example.order_service.domain.repository.OrderItemRepository;
import com.example.order_service.infrastructure.persistence.mapper.OrderItemEntityMapper;
import com.example.order_service.infrastructure.persistence.respository.jpa.OrderItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepository {

    private final OrderItemJpaRepository jpaRepository;
    private final OrderItemEntityMapper mapper;

    @Override
    public OrderItem save(OrderItem orderItem) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(orderItem)));
    }
}
