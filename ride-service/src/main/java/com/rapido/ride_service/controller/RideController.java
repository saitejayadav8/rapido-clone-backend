package com.rapido.ride_service.controller;

import com.rapido.ride_service.dto.RideRequestDTO;
import com.rapido.ride_service.dto.RideResponseDTO;
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

    @PostMapping("/request")
    public ResponseEntity<RideResponseDTO> requestRide(
            @Valid @RequestBody RideRequestDTO dto
    ) {
        Long userId = 1L;
        return ResponseEntity.ok(rideService.requestRide(userId, dto));
    }

    @PutMapping("/{rideId}/accept")
    public ResponseEntity<RideResponseDTO> acceptRide(
            @PathVariable Long rideId
    ) {
        return ResponseEntity.ok(rideService.acceptRide(rideId));
    }
    @PutMapping("/{rideId}/start")
    public ResponseEntity<RideResponseDTO> startRide(
            @PathVariable Long rideId
    ) {
        return ResponseEntity.ok(rideService.startRide(rideId));
    }
    @PutMapping("/{rideId}/complete")
    public ResponseEntity<RideResponseDTO> completeRide(
            @PathVariable Long rideId
    ) {
        return ResponseEntity.ok(rideService.completeRide(rideId));
    }
    @PutMapping("/{rideId}/cancel")
    public ResponseEntity<RideResponseDTO> cancelRide(
            @PathVariable Long rideId
    ) {
        return ResponseEntity.ok(rideService.cancelRide(rideId));
    }
    @GetMapping("/history")
    public ResponseEntity<List<Ride>> getRideHistory() {
        Long userId = 1L; // temporary testing user
        return ResponseEntity.ok(rideService.getRideHistory(userId));
    }
}