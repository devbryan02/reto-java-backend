package com.example.inventory_service.infrastructure.persistence.mapper;

import com.example.inventory_service.domain.model.StockReservation;
import com.example.inventory_service.infrastructure.persistence.entity.StockReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StockReservationEntityMapper {

    StockReservation toDomain(StockReservationEntity entity);
   StockReservationEntity toEntity(StockReservation domain);

}
