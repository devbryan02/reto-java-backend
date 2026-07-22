package com.example.inventory_service.infrastructure.persistence.repository.jpa;

import com.example.inventory_service.infrastructure.persistence.entity.StockReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockReservationJpaRepository extends JpaRepository<StockReservationEntity, UUID> {

}
