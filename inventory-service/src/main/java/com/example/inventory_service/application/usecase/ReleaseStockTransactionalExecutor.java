package com.example.inventory_service.application.usecase;

import com.example.inventory_service.application.dto.ReleaseStockRequest;
import com.example.inventory_service.application.dto.ReleaseStockResponse;
import com.example.inventory_service.domain.enums.ReservationStatus;
import com.example.inventory_service.domain.exception.InventoryNotFoundException;
import com.example.inventory_service.domain.model.Inventory;
import com.example.inventory_service.domain.model.StockReservation;
import com.example.inventory_service.domain.repository.InventoryRepository;
import com.example.inventory_service.domain.repository.StockReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReleaseStockTransactionalExecutor {

    private final InventoryRepository inventoryRepository;
    private final StockReservationRepository stockReservationRepository;

    @Transactional
    public ReleaseStockResponse execute(ReleaseStockRequest request){

        List<StockReservation> reservations = stockReservationRepository.findByOrderId(request.orderId());
        int releasedCount = 0;

        for(StockReservation reservation : reservations){

            // Verificar que la reserva no haya sido liberada
            if(reservation.status() == ReservationStatus.RELEASED) continue;

            Inventory inventory = inventoryRepository.findByProductId(reservation.productId())
                    .orElseThrow(() -> new InventoryNotFoundException("No existe inventario para el producto"));

            Inventory inventoryReleased = inventory.release(reservation.quantity());
            inventoryRepository.save(inventoryReleased);

            releasedCount++;
            log.info("Stock liberado producto={} cantidad={}", reservation.productId(), reservation.quantity());

        }
        return new ReleaseStockResponse(request.orderId(), releasedCount);
    }
}
