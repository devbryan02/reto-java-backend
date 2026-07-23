package com.example.inventory_service.infrastructure.persistence.repository.jpa;

import com.example.inventory_service.domain.enums.ReservationStatus;
import com.example.inventory_service.infrastructure.persistence.entity.StockReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StockReservationJpaRepository extends JpaRepository<StockReservationEntity, UUID> {

    List<StockReservationEntity> findByOrderId(UUID orderId);

    boolean existsByOrderIdAndProductIdAndStatus(UUID orderId, UUID productId, ReservationStatus status);

}
