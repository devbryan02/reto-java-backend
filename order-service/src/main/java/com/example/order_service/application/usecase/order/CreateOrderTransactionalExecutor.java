package com.example.order_service.application.usecase.order;

import com.example.order_service.application.dto.CreateOrderRequest;
import com.example.order_service.application.dto.OrderItemRequest;
import com.example.order_service.domain.enums.OrderStatus;
import com.example.order_service.domain.exception.OrderNotFoundException;
import com.example.order_service.domain.exception.ProductNotFoundException;
import com.example.order_service.domain.model.*;
import com.example.order_service.domain.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
class CreateOrderTransactionalExecutor {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final IdempotencyKeysRepository idempotencyKeysRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final ProductRepository productRepository;

    @Transactional
    public CreateOrderResult execute(CreateOrderRequest request, String idempotencyKey) {

        // 1. Si la key ya fue usada, devolvemos el pedido original sin crear nada
        if (idempotencyKey != null && !idempotencyKey.isBlank()) {
            var existingKey = idempotencyKeysRepository.findByKey(idempotencyKey);
            log.info("IdempotencyKey: {}", idempotencyKey);
            if (existingKey.isPresent()) {
                Order orderExisting = orderRepository.findById(existingKey.get().order().id())
                        .orElseThrow(() -> new OrderNotFoundException("Idempotency apunta a un pedido no innexistente"));
                return new CreateOrderResult(orderExisting, true);
            }
        }

        // 2. Creamos el pedido
        Order order = Order.createOrderPending(request.userId(), BigDecimal.ZERO);
        Order orderSaved = orderRepository.save(order);
        log.info("Pedido creado con id: {}", orderSaved.id());

        // 3. Creamos cada item del pedido y calcular el subtotal
        BigDecimal runningTotal = BigDecimal.ZERO;
        for (OrderItemRequest itemReq : request.items()) {

            Product product = productRepository.findById(itemReq.productId())
                    .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado"));
            log.info("Producto encontrado con id: {}", itemReq.productId());

            BigDecimal unitPrice = BigDecimal.valueOf(product.price());
            BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(itemReq.quantity()));

            runningTotal = runningTotal.add(subtotal);

            OrderItem item = OrderItem.createOrderItem(
                    orderSaved,
                    product,
                    product.name(),
                    unitPrice,
                    itemReq.quantity(),
                    subtotal
            );
            orderItemRepository.save(item);
            log.info("Item creado con id: {}", item.id());
        }

        // 4. Actualizamos el total real del pedido
        Order orderWithTotal = orderSaved.updateTotalAmount(runningTotal);
        Order orderFinal = orderRepository.save(orderWithTotal);
        log.info("Pedido actualizado con total: {}", orderFinal.totalAmount());

        // 5. Se guarda el historial
        OrderHistory history = OrderHistory.saveHistory(
                orderFinal,
                orderFinal.status(),
                "Pedido creado en estado: " + OrderStatus.PENDING
        );
        orderHistoryRepository.save(history);
        log.info("Historial del pedido guardado");

        // 6. Guardamos el idempotencyKey
        if (idempotencyKey != null && !idempotencyKey.isBlank()) {
            var idempotencyKeyEntity = IdempotencyKeys.createIdempotencyKeys(idempotencyKey, orderFinal);
            idempotencyKeysRepository.save(idempotencyKeyEntity);
            log.info("IdempotencyKey guardado: {}", idempotencyKey);
        }

        // 7. Devolvemos el pedido
        return new CreateOrderResult(orderFinal, false);
    }

}