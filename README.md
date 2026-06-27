# Kafka Demo — Spring Boot 4 & Java 21

A simple Kafka producer/consumer demo using Spring Boot 4 and Java 21.

## What it does
- **Producer** exposes a REST endpoint that publishes an `OrderEvent` to Kafka
- **Consumer** listens to the `orders` topic and logs received events

## Requirements
- Java 21
- Docker

## Run

**Start Kafka:**
```bash
docker-compose up -d
```

**Start Producer** (port 8080):
```bash
cd kafka-producer && ./mvnw spring-boot:run
```

**Start Consumer** (port 8081):
```bash
cd kafka-consumer && ./mvnw spring-boot:run
```

**Send an order:**
```bash
curl -X POST http://localhost:8080/api/orders
```

**Send 1000 orders:**
```bash
for i in {1..1000}; do curl -s -X POST http://localhost:8080/api/orders; done
```

## Check messages in topic
```bash
docker exec kafka /opt/kafka/bin/kafka-get-offsets.sh \
  --bootstrap-server localhost:9092 --topic orders
```