package com.rapido.driver_service.controller;

import com.rapido.driver_service.events.PaymentInitiatedEvent;
import com.rapido.driver_service.kafka.KafkaEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ride")
@RequiredArgsConstructor
public class RideCompleteController {

    private final KafkaEventPublisher publisher;

    @PostMapping("/complete")
    public String completeRide() {

        PaymentInitiatedEvent event =
                new PaymentInitiatedEvent();

        event.setRideId(1001L);
        event.setUserId(1L);
        event.setAmount(250.0);

        publisher.publish(
                "payment-initiated",
                event
        );

        return "Ride Completed";
    }
}