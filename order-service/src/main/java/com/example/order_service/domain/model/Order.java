package com.example.order_service.domain.model;

import com.example.order_service.domain.enums.OrderStatus;
import com.example.order_service.domain.exception.InvalidOrderStateTransitionException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record Order(
        UUID id,
        UUID userId,
        OrderStatus status,
        BigDecimal totalAmount,
        Instant createdAt,
        Instant updatedAt
) {

    public static Order createOrderPending(UUID userId, BigDecimal totalAmount) {
        Instant now = Instant.now();
        return new Order(
                null,
                userId,
                OrderStatus.PENDING,
                totalAmount,
                now,
                now
        );
    }

    public Order updateTotalAmount(BigDecimal totalAmount) {
        return new Order(
                this.id,
                this.userId,
                this.status,
                totalAmount,
                this.createdAt,
                Instant.now()
        );
    }

    public Order cancel(){
        if(this.status == OrderStatus.DISPATCHED)
            throw new InvalidOrderStateTransitionException("No se puede cancelar un pedido despachado");

        if(this.status == OrderStatus.CANCELLED || this.status == OrderStatus.FAILED)
            throw new InvalidOrderStateTransitionException("El pedido ya se encuentra en estado " + this.status);

        return new Order(
                this.id,
                this.userId,
                OrderStatus.CANCELLED,
                this.totalAmount,
                this.createdAt,
                Instant.now()
        );
    }

    public Order confirm(){
        if(this.status != OrderStatus.PENDING)
            throw new InvalidOrderStateTransitionException(
                    "Solo se puede confirmar un pedido en estado PENDING, estado actual: " + this.status);

        return new Order(
                this.id,
                this.userId,
                OrderStatus.CONFIRMED,
                this.totalAmount,
                this.createdAt,
                Instant.now()
        );
    }

    public Order fail(){
        if(this.status == OrderStatus.CANCELLED || this.status == OrderStatus.DISPATCHED
                || this.status == OrderStatus.FAILED)
            throw new InvalidOrderStateTransitionException(
                    "No se puede marcar como FAILED un pedido en estado " + this.status);

        return new Order(
                this.id,
                this.userId,
                OrderStatus.FAILED,
                this.totalAmount,
                this.createdAt,
                Instant.now()
        );
    }

}