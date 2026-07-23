package com.example.dispatch_service.infrastructure.persistence.mapper;

import com.example.dispatch_service.domain.model.Dispatch;
import com.example.dispatch_service.infrastructure.persistence.entity.DispatchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DispatchEntityMapper {

    Dispatch toDomain(DispatchEntity entity);
    DispatchEntity toEntity(Dispatch domain);

}
