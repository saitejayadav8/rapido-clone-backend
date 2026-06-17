package com.rapido.search_service.kafka;

import com.rapido.search_service.service.DriverIndexService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DriverUpdatedConsumer {

    private final DriverIndexService indexService;

    @KafkaListener(
            topics = "driver-updated",
            groupId = "search-service"
    )
    public void consume(com.rapido.search_service.kafka.DriverUpdatedEvent event) {

        log.info("Received Driver Update: {}", event);

        indexService.indexDriver(event);
    }
}