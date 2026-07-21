package com.example.order_service.infrastructure.persistence.mapper;

import com.example.order_service.domain.model.OrderHistory;
import com.example.order_service.infrastructure.persistence.entity.OrderHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderHistoryEntityMapper {

    OrderHistory toDomain(OrderHistoryEntity entity);
    OrderHistoryEntity toEntity(OrderHistory domain);

}

