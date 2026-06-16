package com.rapido.notification_service.kafka;

import com.rapido.notification_service.events.RideAssignedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RideAssignedConsumer {

    @KafkaListener(
            topics = "ride-assigned",
            groupId = "notification-service-group"
    )
    public void consume(RideAssignedEvent event) {

        log.info(
                "NOTIFICATION SENT -> Ride={}, Driver={}",
                event.getRideId(),
                event.getDriverId()
        );
    }
}