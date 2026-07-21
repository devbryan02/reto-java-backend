package com.example.order_service.infrastructure.persistence.mapper;

import com.example.order_service.domain.model.IdempotencyKeys;
import com.example.order_service.infrastructure.persistence.entity.IndempotencyKeysEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IdepotencyKeysEntityMapper {

    IdempotencyKeys toDomain(IndempotencyKeysEntity entity);
    IndempotencyKeysEntity toEntity(IdempotencyKeys domain);

}
