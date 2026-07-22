package com.example.order_service.infrastructure.persistence.entity;

import com.example.order_service.domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Table(name = "order_history")
public class OrderHistoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String message;
    private Instant createdAt;

}
