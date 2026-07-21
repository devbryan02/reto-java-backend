package com.example.order_service.infrastructure.persistence.respository.impl;

import com.example.order_service.domain.model.Product;
import com.example.order_service.domain.repository.ProductRepository;
import com.example.order_service.infrastructure.persistence.mapper.ProductEntityMapper;
import com.example.order_service.infrastructure.persistence.respository.jpa.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository jpaRepository;
    private final ProductEntityMapper mapper;

    @Override
    public Product save(Product product) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(product)));
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}
