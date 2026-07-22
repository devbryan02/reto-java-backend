package com.example.order_service.infrastructure.persistence.respository.jpa;

import com.example.order_service.infrastructure.persistence.entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderHistoryJpaRepository extends JpaRepository<OrderHistoryEntity, UUID> {

    List<OrderHistoryEntity> findByOrder_IdOrderByCreatedAtDesc(UUID orderId);

}
