package com.example.inventory_service.infrastructure.persistence.repository.impl;

import com.example.inventory_service.domain.model.StockReservation;
import com.example.inventory_service.domain.repository.StockReservationRepository;
import com.example.inventory_service.infrastructure.persistence.mapper.StockReservationEntityMapper;
import com.example.inventory_service.infrastructure.persistence.repository.jpa.StockReservationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StockReservationRepositoryImpl implements StockReservationRepository {

    private final StockReservationJpaRepository jpaRepository;
    private final StockReservationEntityMapper mapper;

    @Override
    public StockReservation save(StockReservation stockReservation) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(stockReservation)));
    }
}
