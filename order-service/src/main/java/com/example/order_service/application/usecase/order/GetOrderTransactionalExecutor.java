package com.example.order_service.application.usecase.order;

import com.example.order_service.domain.exception.OrderNotFoundException;
import com.example.order_service.domain.model.Order;
import com.example.order_service.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetOrderTransactionalExecutor {

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public Order findOrder(UUID id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("El pedido con id " + id + " no existe"));
    }


}
