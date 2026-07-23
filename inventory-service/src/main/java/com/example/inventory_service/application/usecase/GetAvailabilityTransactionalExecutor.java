package com.example.inventory_service.application.usecase;

import com.example.inventory_service.application.dto.AvailabilityResponse;
import com.example.inventory_service.domain.exception.InventoryNotFoundException;
import com.example.inventory_service.domain.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetAvailabilityTransactionalExecutor {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public AvailabilityResponse execute(UUID productId){
        return inventoryRepository.findByProductId(productId)
                .map(inventory -> new AvailabilityResponse(
                        inventory.productId(),
                        inventory.availableStock())
                )
                .orElseThrow(() -> new InventoryNotFoundException("No existe inventario para el producto"));
    }

}
