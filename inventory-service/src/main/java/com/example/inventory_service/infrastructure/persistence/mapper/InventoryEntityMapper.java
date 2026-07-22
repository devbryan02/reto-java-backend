package com.example.inventory_service.infrastructure.persistence.mapper;

import com.example.inventory_service.domain.model.Inventory;
import com.example.inventory_service.infrastructure.persistence.entity.InventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryEntityMapper {

    Inventory toDomain(InventoryEntity entity);
    InventoryEntity toEntity(Inventory inventory);

}
