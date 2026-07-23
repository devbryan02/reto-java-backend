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
public class FailOrderTransactionalExecutor {

    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    @Transactional
    public Order execute(UUID orderId, String reason){

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Pedido no encontrado"));

        Order orderFailed = order.fail();
        Order orderSaved = orderRepository.save(orderFailed);
        log.info("Pedido marcado como FAILED con id: {}, motivo: {}", orderId, reason);

        OrderHistory history = OrderHistory.saveHistory(
                orderSaved,
                orderSaved.status(),
                reason != null ? reason : "Pedido fallido por la Saga"
        );
        orderHistoryRepository.save(history);

        return orderSaved;
    }

}