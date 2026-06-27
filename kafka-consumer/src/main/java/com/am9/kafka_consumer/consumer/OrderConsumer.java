package com.am9.kafka_consumer.consumer;

import com.am9.kafka_consumer.model.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "orders", groupId = "order-processing-group")
    public void onOrderEvent(OrderEvent event) {
        log.info("Received order: id={} customer={} product={} price={}",
                event.orderId(),
                event.customerId(),
                event.product(),
                event.price());
    }
}