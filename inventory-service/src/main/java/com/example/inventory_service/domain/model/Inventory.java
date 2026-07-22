package com.example.inventory_service.domain.model;

import com.example.inventory_service.domain.exception.InsufficientStockException;

import java.time.Instant;
import java.util.UUID;

public record Inventory(
        UUID productId,
        Integer availableStock,
        Integer reservedStock,
        Instant updatedAt
)
{

    public Inventory reserve(int quantity){

        if(quantity <= 0)
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");

        if(this.availableStock < quantity)
            throw new InsufficientStockException("No hay suficiente stock disponible");

        return new Inventory(
                this.productId,
                this.availableStock - quantity,
                this.reservedStock + quantity,
                Instant.now()
        );
    }

    public Inventory release(int quantity){

        if(quantity <= 0)
            throw new IllegalArgumentException("La cantidad a liberar debe ser mayor a 0");

        int newReserved = Math.max(this.reservedStock - quantity, 0);
        return new Inventory(
                this.productId,
                this.availableStock + quantity,
                newReserved,
                Instant.now()
        );
    }
}
