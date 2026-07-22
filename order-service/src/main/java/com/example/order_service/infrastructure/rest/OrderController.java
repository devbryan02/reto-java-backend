package com.example.order_service.infrastructure.rest;

import com.example.order_service.application.dto.CreateOrderRequest;
import com.example.order_service.application.dto.CreateOrderResponse;
import com.example.order_service.application.dto.OrderHistoryResponse;
import com.example.order_service.application.dto.OrderResponse;
import com.example.order_service.application.usecase.history.GetOrderHistoryUseCase;
import com.example.order_service.application.usecase.order.CancelOrderUseCase;
import com.example.order_service.application.usecase.order.CreateOrderUseCase;
import com.example.order_service.application.usecase.order.GetOrderUseCase;
import com.example.order_service.infrastructure.rest.mapper.OrderHttpMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final GetOrderHistoryUseCase getOrderHistoryUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final OrderHttpMapper httpMapper;

    @PostMapping
    public Mono<ResponseEntity<CreateOrderResponse>> createOrder(
            @RequestBody @Valid CreateOrderRequest request,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey){
        return createOrderUseCase.execute(request, idempotencyKey)
                .map(result -> {
                    OrderResponse orderResponse = httpMapper.toOrderResponse(result.order());
                    CreateOrderResponse body = new CreateOrderResponse(orderResponse, result.alreadyExisted());

                    HttpStatus status = result.alreadyExisted() ? HttpStatus.OK : HttpStatus.CREATED;
                    return ResponseEntity.status(status).body(body);
                });
    }

    @GetMapping("/{orderId}")
    public Mono<ResponseEntity<OrderResponse>> getOrder(@PathVariable("orderId") UUID orderId){
        return getOrderUseCase.execute(orderId)
                .map(order -> ResponseEntity.ok(httpMapper.toOrderResponse(order)));
    }

    @PostMapping("/{orderId}/cancel")
    public Mono<ResponseEntity<OrderResponse>> cancelOrder(@PathVariable("orderId") UUID orderId){
        return cancelOrderUseCase.execute(orderId)
                .map(order -> ResponseEntity.ok(httpMapper.toOrderResponse(order)));
    }

    @GetMapping("/{orderId}/history")
    public Flux<OrderHistoryResponse> getOrderHistory(@PathVariable("orderId") UUID orderId){
        return getOrderHistoryUseCase.execute(orderId)
                .map(httpMapper::toHistoryResponse);
    }
}
