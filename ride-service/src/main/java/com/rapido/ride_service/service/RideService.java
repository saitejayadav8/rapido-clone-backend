package com.rapido.ride_service.service;

import com.rapido.ride_service.dto.RideRequestDTO;
import com.rapido.ride_service.entity.Ride;
import com.rapido.ride_service.repository.RideRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    private final RideRepository rideRepository;

    public RideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    // Create Ride
    public Ride createRide(RideRequestDTO requestDTO) {

        Ride ride = new Ride();

        ride.setUserId(requestDTO.getUserId());
        ride.setDriverId(requestDTO.getDriverId());
        ride.setPickupLocation(requestDTO.getPickupLocation());
        ride.setDropLocation(requestDTO.getDropLocation());
        ride.setFare(requestDTO.getFare());

        ride.setStatus("REQUESTED");

        return rideRepository.save(ride);
    }

    // Get Ride By ID
    public Ride getRideById(Long rideId) {

        return rideRepository.findById(rideId)
                .orElseThrow(() ->
                        new RuntimeException("Ride not found"));
    }

    // Get Rides By User
    public List<Ride> getRidesByUser(Long userId) {

        return rideRepository.findByUserId(userId);
    }

    // Get Rides By Driver
    public List<Ride> getRidesByDriver(Long driverId) {

        return rideRepository.findByDriverId(driverId);
    }

    // Update Ride Status
    public Ride updateRideStatus(Long rideId,
                                 String status) {

        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() ->
                        new RuntimeException("Ride not found"));

        ride.setStatus(status);

        return rideRepository.save(ride);
    }
}