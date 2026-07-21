package com.example.order_service.infrastructure.persistence.mapper;

import com.example.order_service.domain.model.Order;
import com.example.order_service.infrastructure.persistence.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderEntityMapper {

    Order toDomain(OrderEntity entity);
    OrderEntity toEntity(Order domain);

}
