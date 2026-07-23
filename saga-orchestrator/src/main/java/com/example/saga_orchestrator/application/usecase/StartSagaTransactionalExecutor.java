package com.example.saga_orchestrator.application.usecase;

import com.example.saga_orchestrator.application.dto.StartSagaRequest;
import com.example.saga_orchestrator.domain.enums.SagaStep;
import com.example.saga_orchestrator.domain.model.SagaExecution;
import com.example.saga_orchestrator.domain.repository.SagaExecutionRepository;
import com.example.saga_orchestrator.infrastructure.client.dispatch.dto.CreateDispatchClientRequest;
import com.example.saga_orchestrator.infrastructure.client.dispatch.service.DispatchServiceClient;
import com.example.saga_orchestrator.infrastructure.client.inventory.dto.ReleaseStockClientRequest;
import com.example.saga_orchestrator.infrastructure.client.inventory.dto.ReserveStockClientRequest;
import com.example.saga_orchestrator.infrastructure.client.inventory.dto.StockItemClientRequest;
import com.example.saga_orchestrator.infrastructure.client.inventory.service.InventoryServiceClient;
import com.example.saga_orchestrator.infrastructure.client.order.OrderServiceClient;
import com.example.saga_orchestrator.infrastructure.client.order.dto.CreateOrderClientRequest;
import com.example.saga_orchestrator.infrastructure.client.order.dto.CreateOrderClientResponse;
import com.example.saga_orchestrator.infrastructure.client.order.dto.OrderItemClientRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class StartSagaTransactionalExecutor {

    private final SagaExecutionRepository sagaExecutionRepository;
    private final OrderServiceClient orderServiceClient;
    private final InventoryServiceClient inventoryServiceClient;
    private final DispatchServiceClient dispatchServiceClient;

    public SagaExecution execute(StartSagaRequest request, String traceId) {

        // PASO 1: Crear el pedido
        CreateOrderClientRequest orderRequest = new CreateOrderClientRequest(
                request.userId(),
                request.items().stream()
                        .map(item -> new OrderItemClientRequest(item.productId(), item.quantity()))
                        .toList()
        );

        CreateOrderClientResponse orderResponse = orderServiceClient
                .createOrder(orderRequest, traceId)
                .block();

        assert orderResponse != null;
        UUID orderId = orderResponse.order().id();
        log.info("[{}] Paso 1 OK - Pedido creado con id: {}", traceId, orderId);

        SagaExecution saga = SagaExecution.start(orderId);
        saga = sagaExecutionRepository.save(saga);

        // 2: Reservar inventario
        try {
            ReserveStockClientRequest reserveRequest = new ReserveStockClientRequest(
                    orderId,
                    request.items().stream()
                            .map(item -> new StockItemClientRequest(item.productId(), item.quantity()))
                            .toList()
            );

            inventoryServiceClient.reserveStock(reserveRequest, traceId).block();
            log.info("[{}] Paso 2 OK - Stock reservado para pedido: {}", traceId, orderId);

            saga = saga.advanceTo(SagaStep.STOCK_RESERVED);
            saga = sagaExecutionRepository.save(saga);

        } catch (Exception ex) {
            log.error("[{}] Paso 2 FALLO - No se pudo reservar stock para pedido: {}", traceId, orderId, ex);
            return compensate(saga, orderId, "Fallo al reservar inventario: " + extractMessage(ex), traceId);
        }

        // PASO 3: Crear despacho
        try {
            CreateDispatchClientRequest dispatchRequest = new CreateDispatchClientRequest(orderId);
            dispatchServiceClient.createDispatch(dispatchRequest, traceId).block();
            log.info("[{}] Paso 3 OK - Dispatch creado para pedido: {}", traceId, orderId);

            saga = saga.advanceTo(SagaStep.DISPATCH_CREATED);
            saga = sagaExecutionRepository.save(saga);

        } catch (Exception ex) {
            log.error("[{}] Paso 3 FALLO - No se pudo crear dispatch para pedido: {}", traceId, orderId, ex);
            return compensate(saga, orderId, "Fallo al crear despacho: " + extractMessage(ex), traceId);
        }

        // PASO 4: Confirmar pedido
        orderServiceClient.confirmOrder(orderId, traceId).block();
        log.info("[{}] Paso 4 OK - Pedido confirmado: {}", traceId, orderId);

        saga = saga.complete();
        saga = sagaExecutionRepository.save(saga);

        log.info("[{}] Saga COMPLETED para pedido: {}", traceId, orderId);
        return saga;
    }

    /**
     * Ejecuta la compensación según el paso alcanzado (currentStep) antes del fallo.
     * Solo se libera lo que efectivamente se llegó a reservar/crear.
     */
    private SagaExecution compensate(SagaExecution saga, UUID orderId, String reason, String traceId) {

        saga = saga.startCompensation(reason);
        saga = sagaExecutionRepository.save(saga);
        log.warn("[{}] Iniciando compensación para pedido: {} - motivo: {}", traceId, orderId, reason);

        try {
            // Si ya se reservó stock o paso el siguiente
            if (saga.currentStep() == SagaStep.STOCK_RESERVED || saga.currentStep() == SagaStep.DISPATCH_CREATED) {

                inventoryServiceClient.releaseStock(new ReleaseStockClientRequest(orderId), traceId).block();
                log.info("[{}] Compensación: stock liberado para pedido: {}", traceId, orderId);
            }

            // El pedido siempre se marca como FAILED
            orderServiceClient.failOrder(orderId, reason, traceId).block();
            log.info("[{}] Compensación: pedido marcado FAILED: {}", traceId, orderId);

            saga = saga.compensated();
            saga = sagaExecutionRepository.save(saga);
            log.warn("[{}] Saga COMPENSATED para pedido: {}", traceId, orderId);

        } catch (Exception compensationEx) {
            log.error("[{}] Fallo la compensación para pedido: {}", traceId, orderId, compensationEx);
            saga = saga.fail("Fallo en compensación: " + extractMessage(compensationEx));
            saga = sagaExecutionRepository.save(saga);
        }

        return saga;
    }

    private String extractMessage(Exception ex) {
        if (ex instanceof WebClientResponseException webEx) {
            return webEx.getStatusCode() + " - " + webEx.getResponseBodyAsString();
        }
        return ex.getMessage();
    }

}