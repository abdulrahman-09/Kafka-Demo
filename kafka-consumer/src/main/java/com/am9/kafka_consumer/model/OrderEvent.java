package com.am9.kafka_consumer.model;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderEvent(
    String orderId,
    String customerId,
    String product,
    int quantity,
    BigDecimal price,
    Instant eventTime
) {}