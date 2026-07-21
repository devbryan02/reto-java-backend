package com.example.order_service.domain.repository;

import com.example.order_service.domain.model.OrderHistory;

public interface OrderHistoryRepository {

    OrderHistory save(OrderHistory orderHistory);

}
