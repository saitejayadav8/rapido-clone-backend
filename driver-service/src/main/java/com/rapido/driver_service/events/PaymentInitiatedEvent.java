package com.rapido.driver_service.events;

import lombok.Data;

@Data
public class PaymentInitiatedEvent {

    private Long rideId;
    private Long userId;
    private Double amount;
}