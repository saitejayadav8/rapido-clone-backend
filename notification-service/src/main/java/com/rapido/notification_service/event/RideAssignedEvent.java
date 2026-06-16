package com.rapido.notification_service.events;

import lombok.Data;

@Data
public class RideAssignedEvent {

    private Long rideId;
    private Long driverId;
    private String status;
}