package com.rapido.notification_service.kafka;

import com.rapido.notification_service.events.PaymentSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentSuccessConsumer {

    @KafkaListener(
            topics = "payment-success",
            groupId = "notification-service-group"
    )
    public void consume(PaymentSuccessEvent event) {

        log.info(
                "PAYMENT SUCCESSFUL -> Ride={}, Amount={}",
                event.getRideId(),
                event.getAmount()
        );
    }
}