package com.example.order_service.domain.repository;

import com.example.order_service.domain.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    Product save(Product product);
    Optional<Product> findById(UUID id);

}
