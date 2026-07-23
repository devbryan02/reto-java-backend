package com.example.inventory_service.infrastructure.rest.inventory;

import com.example.inventory_service.application.dto.*;
import com.example.inventory_service.application.usecase.GetAvailabilityUseCase;
import com.example.inventory_service.application.usecase.ReleaseStockUseCase;
import com.example.inventory_service.application.usecase.ReserveStockUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
@Tag(name = "Inventory", description = "Inventory Service API")
public class InventoryController {

    private final ReserveStockUsecase reserveStockUsecase;
    private final ReleaseStockUseCase releaseStockUseCase;
    private final GetAvailabilityUseCase getAvailabilityUseCase;

    @PostMapping("/reservations")
    @Operation(summary = "Reserve stock for an order")
    public Mono<ResponseEntity<ReserveStockResponse>> reserveStock(
            @RequestBody @Valid ReserveStockRequest request){
        return reserveStockUsecase.execute(request).map(ResponseEntity::ok);
    }

    @PostMapping("/reservations/release")
    @Operation(summary = "Release stock reserved for an order")
    public Mono<ResponseEntity<ReleaseStockResponse>> releaseStock(
            @RequestBody @Valid ReleaseStockRequest request){
        return releaseStockUseCase.execute(request).map(ResponseEntity::ok);
    }

    @GetMapping("/availability")
    @Operation(summary = "Get available stock for a product")
    public Mono<ResponseEntity<AvailabilityResponse>> getAvailability(
            @RequestParam("productId") UUID productId) {
        return getAvailabilityUseCase.execute(productId).map(ResponseEntity::ok);
    }

}
