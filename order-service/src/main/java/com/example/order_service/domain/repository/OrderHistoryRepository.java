package com.example.order_service.domain.repository;

import com.example.order_service.domain.model.OrderHistory;

import java.util.List;
import java.util.UUID;

public interface OrderHistoryRepository {

    OrderHistory save(OrderHistory orderHistory);
    List<OrderHistory> findByOrderId(UUID orderId);

}
