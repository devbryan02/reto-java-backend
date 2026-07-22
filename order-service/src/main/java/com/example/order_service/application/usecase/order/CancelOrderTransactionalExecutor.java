package com.example.order_service.application.usecase.order;

import com.example.order_service.domain.exception.OrderNotFoundException;
import com.example.order_service.domain.model.Order;
import com.example.order_service.domain.model.OrderHistory;
import com.example.order_service.domain.repository.OrderHistoryRepository;
import com.example.order_service.domain.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CancelOrderTransactionalExecutor {

    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    @Transactional
    public Order execute(UUID orderId){

        // Buscar el pedido por id
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Pedido no encontrado"));
        log.info("Pedido encontrado con id: {}", orderId);

        // Cambiar el estado del pedido a CANCELLED
        Order OrderCanceled = order.cancel();
        Order orderSaved = orderRepository.save(OrderCanceled);
        log.info("Pedido cancelado con id: {}", orderId);

        // Guardar el historial del pedido
        OrderHistory history = OrderHistory.saveHistory(
                orderSaved,
                orderSaved.status(),
                "Pedido cancelado por el usuario"
        );
        orderHistoryRepository.save(history);
        log.info("Historial del pedido guardado");

        return orderSaved;
    }

}
