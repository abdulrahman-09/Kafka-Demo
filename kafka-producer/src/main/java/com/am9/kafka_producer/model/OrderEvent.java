package com.am9.kafka_producer.model;

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