package com.rapido.ride_service.controller;

import com.rapido.ride_service.dto.RideRequestDTO;
import com.rapido.ride_service.entity.Ride;
import com.rapido.ride_service.service.RideService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rides")
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Ride Service Working");
    }

    @PostMapping("/request")
    public ResponseEntity<Ride> createRide(
            @Valid @RequestBody RideRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(rideService.createRide(requestDTO));
    }

    @GetMapping("/{rideId}")
    public ResponseEntity<Ride> getRideById(
            @PathVariable Long rideId
    ) {
        return ResponseEntity.ok(rideService.getRideById(rideId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ride>> getRidesByUser(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(rideService.getRidesByUser(userId));
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Ride>> getRidesByDriver(
            @PathVariable Long driverId
    ) {
        return ResponseEntity.ok(rideService.getRidesByDriver(driverId));
    }

    @PutMapping("/{rideId}/status")
    public ResponseEntity<Ride> updateRideStatus(
            @PathVariable Long rideId,
            @RequestParam String status
    ) {
        return ResponseEntity.ok(
                rideService.updateRideStatus(rideId, status)
        );
    }
}