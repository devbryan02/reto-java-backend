package com.example.saga_orchestrator.infrastructure.persistence.mapper;

import com.example.saga_orchestrator.domain.model.SagaExecution;
import com.example.saga_orchestrator.infrastructure.persistence.entity.SagaExecutionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SagaExecutionEntityMapper {

    SagaExecution toDomain(SagaExecutionEntity entity);
    SagaExecutionEntity toEntity(SagaExecution domain);

}