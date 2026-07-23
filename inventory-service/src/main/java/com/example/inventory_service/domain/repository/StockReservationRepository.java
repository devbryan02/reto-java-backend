package com.example.inventory_service.domain.repository;

import com.example.inventory_service.domain.enums.ReservationStatus;
import com.example.inventory_service.domain.model.StockReservation;

import java.util.List;
import java.util.UUID;

public interface StockReservationRepository {

    StockReservation save(StockReservation stockReservation);
    List<StockReservation> findByOrderId(UUID orderId);
    boolean existsByOrderIdAndProductIdAndStatus(UUID orderId, UUID productId, ReservationStatus status);

}
