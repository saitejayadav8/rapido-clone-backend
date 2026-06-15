package com.rapido.ride_service.controller;

import com.rapido.ride_service.entity.Ride;
import com.rapido.ride_service.repository.RideRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rides")
public class RideController {

    private final RideRepository rideRepository;

    public RideController(
            RideRepository rideRepository) {

        this.rideRepository = rideRepository;
    }

    @GetMapping("/{rideId}")
    @PreAuthorize(
            "@rideSecurity.canAccessRide(#rideId, authentication.name)"
                    + " || hasRole('SUPER_ADMIN')")
    public Ride getRide(
            @PathVariable Long rideId) {

        return rideRepository
                .findById(rideId)
                .orElseThrow();
    }
}