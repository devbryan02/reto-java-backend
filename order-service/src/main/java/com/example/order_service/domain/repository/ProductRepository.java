package com.example.order_service.domain.repository;

import com.example.order_service.domain.model.Product;

public interface ProductRepository {

    Product save(Product product);

}
