package com.example.dispatch_service.domain.model;

import com.example.dispatch_service.domain.enums.DispatchStatus;

import java.time.Instant;
import java.util.UUID;

public record Dispatch(
        UUID id,
        UUID orderId,
        String trackingNumber,
        DispatchStatus status,
        Instant createdAt,
        Instant updatedAt
)
{

    public static Dispatch createDispatch(UUID orderId){
        Instant now = Instant.now();
        return new Dispatch(
                null,
                orderId,
                generateTrackingNumber(),
                DispatchStatus.CREATED,
                now,
                now
        );
    }

    private static String generateTrackingNumber(){
        return "TRK-" + UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 12)
                .toUpperCase();
    }
}
