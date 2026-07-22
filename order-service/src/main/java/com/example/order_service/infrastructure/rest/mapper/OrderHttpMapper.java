package com.example.order_service.infrastructure.rest.mapper;

import com.example.order_service.application.dto.OrderHistoryResponse;
import com.example.order_service.application.dto.OrderResponse;
import com.example.order_service.domain.model.Order;
import com.example.order_service.domain.model.OrderHistory;
import org.springframework.stereotype.Component;

@Component
public class OrderHttpMapper {

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.id(),
                order.userId(),
                order.status(),
                order.totalAmount(),
                order.createdAt(),
                order.updatedAt()
        );
    }

    public OrderHistoryResponse toHistoryResponse(OrderHistory history) {
        return new OrderHistoryResponse(
                history.id(),
                history.status(),
                history.message(),
                history.createdAt()
        );
    }
}
