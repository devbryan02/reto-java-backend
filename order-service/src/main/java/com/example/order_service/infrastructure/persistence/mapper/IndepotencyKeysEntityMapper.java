package com.example.order_service.infrastructure.persistence.mapper;

import com.example.order_service.domain.model.IndempotencyKeys;
import com.example.order_service.infrastructure.persistence.entity.IndempotencyKeysEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IndepotencyKeysEntityMapper {

    IndempotencyKeys toDomain(IndempotencyKeysEntity entity);
    IndempotencyKeysEntity toEntity(IndempotencyKeys domain);

}
