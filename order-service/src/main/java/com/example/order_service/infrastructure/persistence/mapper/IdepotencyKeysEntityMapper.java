package com.example.order_service.infrastructure.persistence.mapper;

import com.example.order_service.domain.model.IdempotencyKeys;
import com.example.order_service.infrastructure.persistence.entity.IdempotencyKeysEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IdepotencyKeysEntityMapper {

    IdempotencyKeys toDomain(IdempotencyKeysEntity entity);
    IdempotencyKeysEntity toEntity(IdempotencyKeys domain);

}
