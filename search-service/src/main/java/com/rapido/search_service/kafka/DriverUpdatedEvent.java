package com.rapido.search_service.kafka;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverUpdatedEvent {

    private Long driverId;

    private String name;

    private Double rating;

    private String vehicleType;

    private Double latitude;

    private Double longitude;
}