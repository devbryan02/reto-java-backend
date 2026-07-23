package com.example.inventory_service.application.usecase;

import com.example.inventory_service.application.dto.ReservationItemResponse;
import com.example.inventory_service.application.dto.ReserveStockResponse;
import com.example.inventory_service.application.dto.ReserveStockRequest;
import com.example.inventory_service.application.dto.StockItemRequest;
import com.example.inventory_service.domain.enums.ReservationStatus;
import com.example.inventory_service.domain.exception.DuplicateReservationException;
import com.example.inventory_service.domain.exception.InventoryNotFoundException;
import com.example.inventory_service.domain.model.Inventory;
import com.example.inventory_service.domain.model.StockReservation;
import com.example.inventory_service.domain.repository.InventoryRepository;
import com.example.inventory_service.domain.repository.StockReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReserveStockTransactionalExecutor {

    private final InventoryRepository inventoryRepository;
    private final StockReservationRepository stockReservationRepository;

    @Transactional
    public ReserveStockResponse execute(ReserveStockRequest request){

        List<ReservationItemResponse> reservedItems = new ArrayList<>();

        for(StockItemRequest itemRequest : request.items()){

            // 1. Evitar reservas duplicadas para el mismo pedido
            boolean alreadyReserved = stockReservationRepository.existsByOrderIdAndProductIdAndStatus(
                    request.orderId(),
                    itemRequest.productId(),
                    ReservationStatus.RESERVED
            );

            if(alreadyReserved)
                throw new DuplicateReservationException("Ya existe una reserva para este producto");

            // 2. Buscar el inventario del producto
            Inventory inventory = inventoryRepository.findByProductId(itemRequest.productId())
                    .orElseThrow(() -> new InventoryNotFoundException("Producto no encontrado"));

            // 3. Reservar con validaciones
            Inventory inventoryReserved = inventory.reserve(itemRequest.quantity());
            inventoryRepository.save(inventoryReserved);
            log.info("Stock reservado producto={} cantidad={}", itemRequest.productId(), itemRequest.quantity());

            // 4. Se guarda la reserva
            StockReservation stockReservation = StockReservation.createReservation(
                    request.orderId(),
                    itemRequest.productId(),
                    itemRequest.quantity()
            );
            stockReservationRepository.save(stockReservation);
            log.info("Reserva guardada con id: {}", stockReservation.id());

            // 5. Se guarda el detalle de la reserva
            reservedItems.add(new ReservationItemResponse(itemRequest.productId(), itemRequest.quantity()));

        }
        return new ReserveStockResponse(request.orderId(), reservedItems);
    }
}
