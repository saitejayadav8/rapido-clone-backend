package com.rapido.matching_service.dto;

import lombok.Data;

@Data
public class RideRequestDto {

    private Long rideId;

    private Double pickupLat;

    private Double pickupLon;

    private String vehicleType;
}