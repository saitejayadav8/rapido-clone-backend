package com.rapido.ride_service.repository;

import com.rapido.ride_service.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository
        extends JpaRepository<Ride, Long> {
}