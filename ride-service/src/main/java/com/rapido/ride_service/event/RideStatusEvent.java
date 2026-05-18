package com.rapido.ride_service.event;

import com.rapido.ride_service.entity.RideStatus;
import java.time.LocalDateTime;

public class RideStatusEvent {

    private Long rideId;
    private RideStatus status;
    private String message;
    private LocalDateTime timestamp;

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}