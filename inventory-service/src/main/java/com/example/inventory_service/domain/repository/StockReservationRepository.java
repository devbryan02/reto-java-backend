package com.example.inventory_service.domain.repository;

import com.example.inventory_service.domain.model.StockReservation;

public interface StockReservationRepository {

    StockReservation save(StockReservation stockReservation);

}
