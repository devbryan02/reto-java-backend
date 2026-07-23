package com.example.dispatch_service.infrastructure.persistence.repository.jpa;


import com.example.dispatch_service.infrastructure.persistence.entity.DispatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DispatchJpaRepository extends JpaRepository<DispatchEntity, UUID> {

    Optional<DispatchEntity> findByOrderId(UUID orderId);

}
