package com.example.inventory_service.infrastructure.persistence.repository.impl;

import com.example.inventory_service.domain.model.Inventory;
import com.example.inventory_service.domain.repository.InventoryRepository;
import com.example.inventory_service.infrastructure.persistence.mapper.InventoryEntityMapper;
import com.example.inventory_service.infrastructure.persistence.repository.jpa.InventoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class InventoryRepositoryImpl implements InventoryRepository {

    private final InventoryJpaRepository jpaRepository;
    private final InventoryEntityMapper mapper;

    @Override
    public Inventory save(Inventory inventory) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(inventory)));
    }

    @Override
    public Optional<Inventory> findByProductId(UUID productId) {
        return jpaRepository.findByProductId(productId).map(mapper::toDomain);
    }
}
