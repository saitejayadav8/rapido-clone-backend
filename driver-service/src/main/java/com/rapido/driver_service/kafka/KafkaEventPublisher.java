package com.rapido.driver_service.kafka;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(String topic, Object event) {

        kafkaTemplate.send(topic, event);

        log.info(
                "EVENT PUBLISHED -> Topic={}, Event={}",
                topic,
                event
        );
    }
}