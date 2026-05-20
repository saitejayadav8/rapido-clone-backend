package com.rapido.ride_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RideRequestDTO {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Driver ID is required")
    private Long driverId;

    @NotBlank(message = "Pickup location is required")
    private String pickupLocation;

    @NotBlank(message = "Drop location is required")
    private String dropLocation;

    @NotNull(message = "Fare is required")
    private Double fare;

    public Long getUserId() {
        return userId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public Double getFare() {
        return fare;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }
}