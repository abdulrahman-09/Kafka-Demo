package com.am9.kafka_producer.controller;


import com.am9.kafka_producer.model.OrderEvent;
import com.am9.kafka_producer.producer.OrderProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public ResponseEntity<String> placeOrder() {
        var event = new OrderEvent(
            UUID.randomUUID().toString(),
            "CUST-" + (int)(Math.random() * 1000),
            "Widget Pro",
            2,
            new BigDecimal("49.99"),
            Instant.now()
        );
        orderProducer.send(event);
        return ResponseEntity.accepted().body("Order queued: " + event.orderId());
    }
}