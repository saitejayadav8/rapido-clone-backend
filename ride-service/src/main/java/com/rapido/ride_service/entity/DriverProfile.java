package com.rapido.ride_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DriverProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;

    private Double longitude;

    private Boolean online = false;

    private Boolean available = true;
}