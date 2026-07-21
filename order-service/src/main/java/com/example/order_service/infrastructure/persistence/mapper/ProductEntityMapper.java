package com.example.order_service.infrastructure.persistence.mapper;

import com.example.order_service.domain.model.Product;
import com.example.order_service.infrastructure.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductEntityMapper {

    Product toDomain(ProductEntity entity);
    ProductEntity toEntity(Product domain);

}
