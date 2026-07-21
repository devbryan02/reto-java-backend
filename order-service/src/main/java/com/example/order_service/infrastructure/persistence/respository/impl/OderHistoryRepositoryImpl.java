package com.example.order_service.infrastructure.persistence.respository.impl;

import com.example.order_service.domain.model.OrderHistory;
import com.example.order_service.domain.repository.OrderHistoryRepository;
import com.example.order_service.infrastructure.persistence.mapper.OrderHistoryEntityMapper;
import com.example.order_service.infrastructure.persistence.respository.jpa.OrderHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OderHistoryRepositoryImpl implements OrderHistoryRepository {

    private final OrderHistoryJpaRepository jpaRepository;
    private final OrderHistoryEntityMapper mapper;

    @Override
    public OrderHistory save(OrderHistory orderHistory) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(orderHistory)));
    }
}
