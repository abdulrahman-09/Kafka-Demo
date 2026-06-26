package com.am9.kafka_producer.producer;

import com.am9.kafka_producer.config.KafkaTopicConfig;
import com.am9.kafka_producer.model.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);


    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(OrderEvent event) {
        kafkaTemplate.send(KafkaTopicConfig.ORDERS_TOPIC, event.orderId(), event)
            .whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Sent order={} → partition={} offset={}",
                        event.orderId(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
                } else {
                    log.error("Failed to send order={}: {}", event.orderId(), ex.getMessage());
                }
            });
    }
}