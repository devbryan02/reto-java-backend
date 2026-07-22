package com.example.order_service.application.usecase.history;

import com.example.order_service.domain.model.OrderHistory;
import com.example.order_service.domain.repository.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetOrderHistoryTransactionalExecutor {

    private final OrderHistoryRepository repository;

    @Transactional(readOnly = true)
    public List<OrderHistory> execute(UUID orderId){
        return repository.findByOrderId(orderId);
    }

}
