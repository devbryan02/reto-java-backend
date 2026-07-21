package com.example.order_service.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItem(
    UUID id,
    Order order,
    Product product,
    String productName,
    BigDecimal unitPrice,
    int quantity,
    BigDecimal subtotal
) { }
