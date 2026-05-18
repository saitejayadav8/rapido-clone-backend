package com.rapido.ride_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "rides",
        indexes = {
                @Index(columnList = "userId"),
                @Index(columnList = "driverId"),
                @Index(columnList = "status")
        }
)
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long driverId;

    private Double pickupLatitude;

    private Double pickupLongitude;

    private Double dropLatitude;

    private Double dropLongitude;

    @Enumerated(EnumType.STRING)
    private RideStatus status;

    private Double estimatedDistance;

    private Double estimatedFare;

    private LocalDateTime requestedAt;

    private LocalDateTime completedAt;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getDriverId() {
        return driverId;
    }

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

    public RideStatus getStatus() {
        return status;
    }

    public Double getEstimatedDistance() {
        return estimatedDistance;
    }

    public Double getEstimatedFare() {
        return estimatedFare;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
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

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public void setEstimatedDistance(Double estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }

    public void setEstimatedFare(Double estimatedFare) {
        this.estimatedFare = estimatedFare;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}