package com.rapido.matching_service.dto;

import lombok.Data;

@Data
public class DriverDto {

    private Long driverId;

    private String name;

    private Double rating;

    private String vehicleType;
}