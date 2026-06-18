package com.rapido.matching_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DriverMatchResponse {

    private Long rideId;

    private Long driverId;

    private String driverName;

    private String status;
}