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
public class ConfirmOrderTransactionalExecutor {

    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    @Transactional
    public Order execute(UUID orderId){

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Pedido no encontrado"));

        Order orderConfirmed = order.confirm();
        Order orderSaved = orderRepository.save(orderConfirmed);
        log.info("Pedido confirmado con id: {}", orderId);

        OrderHistory history = OrderHistory.saveHistory(
                orderSaved,
                orderSaved.status(),
                "Pedido confirmado por la Saga"
        );
        orderHistoryRepository.save(history);

        return orderSaved;
    }

}