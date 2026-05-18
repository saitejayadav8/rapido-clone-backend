package com.rapido.ride_service.dto;

import jakarta.validation.constraints.NotNull;

public class RideRequestDTO {

    @NotNull(message = "Pickup latitude is required")
    private Double pickupLatitude;

    @NotNull(message = "Pickup longitude is required")
    private Double pickupLongitude;

    @NotNull(message = "Drop latitude is required")
    private Double dropLatitude;

    @NotNull(message = "Drop longitude is required")
    private Double dropLongitude;

    public Double getPickupLatitude() {
        return pickupLatitude;
    }

    public Double getPickupLongitude() {
        return pickupLongitude;
    }

    public Double getDropLatitude() {
        return dropLatitude;
    }

    public Double getDropLongitude() {
        return dropLongitude;
    }

    public void setPickupLatitude(Double pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    public void setPickupLongitude(Double pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    public void setDropLatitude(Double dropLatitude) {
        this.dropLatitude = dropLatitude;
    }

    public void setDropLongitude(Double dropLongitude) {
        this.dropLongitude = dropLongitude;
    }
}