package com.rapido.payment_service.kafka;

import com.rapido.payment_service.events.PaymentInitiatedEvent;
import com.rapido.payment_service.events.PaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentInitiatedConsumer {

    private final KafkaEventPublisher publisher;

    @KafkaListener(
            topics = "payment-initiated",
            groupId = "payment-service-group"
    )
    public void consume(
            PaymentInitiatedEvent event) {

        log.info(
                "PAYMENT RECEIVED -> {}",
                event
        );

        PaymentSuccessEvent success =
                new PaymentSuccessEvent();

        success.setRideId(
                event.getRideId()
        );

        success.setUserId(
                event.getUserId()
        );

        success.setAmount(
                event.getAmount()
        );

        success.setStatus(
                "PAYMENT_SUCCESS"
        );

        publisher.publish(
                "payment-success",
                success
        );
    }
}