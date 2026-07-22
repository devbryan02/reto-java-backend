package com.example.inventory_service.domain.model;

import com.example.inventory_service.domain.enums.ReservationStatus;

import java.time.Instant;
import java.util.UUID;

public record StockReservation(
        UUID id,
        UUID orderId,
        UUID productId,
        Integer quantity,
        ReservationStatus status,
        Instant createdAt
) {

    public static StockReservation createReservation(UUID orderId, UUID productId, Integer quantity) {
        return new StockReservation(
                null,
                orderId,
                productId,
                quantity,
                ReservationStatus.RESERVED,
                Instant.now()
        );
    }

    public StockReservation release(){
        return new StockReservation(
                this.id,
                this.orderId,
                this.productId,
                this.quantity,
                ReservationStatus.RELEASED,
                this.createdAt
        );
    }
}
