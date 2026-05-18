package com.rapido.ride_service.service;

import com.rapido.ride_service.algorithm.DriverMatchingAlgorithm;
import com.rapido.ride_service.dto.RideRequestDTO;
import com.rapido.ride_service.dto.RideResponseDTO;
import com.rapido.ride_service.entity.Driver;
import com.rapido.ride_service.entity.Ride;
import com.rapido.ride_service.entity.RideStatus;
import com.rapido.ride_service.repository.DriverRepository;
import com.rapido.ride_service.repository.RideRepository;
import com.rapido.ride_service.util.DistanceUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RideService {

    private final RideRepository rideRepository;
    private final DriverRepository driverRepository;
    private final DriverMatchingAlgorithm driverMatchingAlgorithm;

    public RideService(RideRepository rideRepository,
                       DriverRepository driverRepository,
                       DriverMatchingAlgorithm driverMatchingAlgorithm) {
        this.rideRepository = rideRepository;
        this.driverRepository = driverRepository;
        this.driverMatchingAlgorithm = driverMatchingAlgorithm;
    }

    @Transactional

    public RideResponseDTO startRide(Long rideId) {

        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        if (ride.getStatus() != RideStatus.ACCEPTED) {
            throw new RuntimeException("Ride cannot be started");
        }

        ride.setStatus(RideStatus.STARTED);

        Ride updatedRide = rideRepository.save(ride);

        RideResponseDTO response = new RideResponseDTO();
        response.setRideId(updatedRide.getId());
        response.setUserId(updatedRide.getUserId());
        response.setDriverId(updatedRide.getDriverId());
        response.setStatus(updatedRide.getStatus());
        response.setEstimatedDistance(updatedRide.getEstimatedDistance());
        response.setEstimatedFare(updatedRide.getEstimatedFare());
        response.setMessage("Ride started successfully");

        return response;
    }
    @Transactional
    public RideResponseDTO requestRide(Long userId, RideRequestDTO dto) {

        List<RideStatus> activeStatuses = List.of(
                RideStatus.REQUESTED,
                RideStatus.ACCEPTED,
                RideStatus.STARTED
        );

        rideRepository.findByUserIdAndStatusIn(userId, activeStatuses)
                .ifPresent(ride -> {
                    throw new RuntimeException("User already has active ride");
                });

        List<Driver> availableDrivers =
                driverRepository.findByAvailableTrueAndOnlineTrue();

        if (availableDrivers.isEmpty()) {
            throw new RuntimeException("No drivers available");
        }

        Driver nearestDriver = driverMatchingAlgorithm.findNearestDriver(
                availableDrivers,
                dto.getPickupLatitude(),
                dto.getPickupLongitude()
        );

        nearestDriver.setAvailable(false);
        driverRepository.save(nearestDriver);

        double distance = DistanceUtil.calculateDistance(
                dto.getPickupLatitude(),
                dto.getPickupLongitude(),
                dto.getDropLatitude(),
                dto.getDropLongitude()
        );

        double fare = 40 + (distance * 12);

        Ride ride = new Ride();
        ride.setUserId(userId);
        ride.setDriverId(nearestDriver.getId());
        ride.setPickupLatitude(dto.getPickupLatitude());
        ride.setPickupLongitude(dto.getPickupLongitude());
        ride.setDropLatitude(dto.getDropLatitude());
        ride.setDropLongitude(dto.getDropLongitude());
        ride.setStatus(RideStatus.REQUESTED);
        ride.setEstimatedDistance(distance);
        ride.setEstimatedFare(fare);
        ride.setRequestedAt(LocalDateTime.now());

        Ride savedRide = rideRepository.save(ride);

        RideResponseDTO response = new RideResponseDTO();
        response.setRideId(savedRide.getId());
        response.setUserId(savedRide.getUserId());
        response.setDriverId(savedRide.getDriverId());
        response.setStatus(savedRide.getStatus());
        response.setEstimatedDistance(savedRide.getEstimatedDistance());
        response.setEstimatedFare(savedRide.getEstimatedFare());
        response.setMessage("Ride requested successfully");

        return response;
    }

    @Transactional
    public RideResponseDTO acceptRide(Long rideId) {

        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        if (ride.getStatus() != RideStatus.REQUESTED) {
            throw new RuntimeException("Ride cannot be accepted");
        }

        ride.setStatus(RideStatus.ACCEPTED);

        Ride updatedRide = rideRepository.save(ride);

        RideResponseDTO response = new RideResponseDTO();
        response.setRideId(updatedRide.getId());
        response.setUserId(updatedRide.getUserId());
        response.setDriverId(updatedRide.getDriverId());
        response.setStatus(updatedRide.getStatus());
        response.setEstimatedDistance(updatedRide.getEstimatedDistance());
        response.setEstimatedFare(updatedRide.getEstimatedFare());
        response.setMessage("Ride accepted successfully");

        return response;
    }
    @Transactional
    public RideResponseDTO completeRide(Long rideId) {

        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        if (ride.getStatus() != RideStatus.STARTED) {
            throw new RuntimeException("Ride cannot be completed");
        }

        ride.setStatus(RideStatus.COMPLETED);
        ride.setCompletedAt(LocalDateTime.now());

        Driver driver = driverRepository.findById(ride.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driver.setAvailable(true);
        driverRepository.save(driver);

        Ride updatedRide = rideRepository.save(ride);

        RideResponseDTO response = new RideResponseDTO();
        response.setRideId(updatedRide.getId());
        response.setUserId(updatedRide.getUserId());
        response.setDriverId(updatedRide.getDriverId());
        response.setStatus(updatedRide.getStatus());
        response.setEstimatedDistance(updatedRide.getEstimatedDistance());
        response.setEstimatedFare(updatedRide.getEstimatedFare());
        response.setMessage("Ride completed successfully");

        return response;
    }
    @Transactional
    public RideResponseDTO cancelRide(Long rideId) {

        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        if (ride.getStatus() == RideStatus.COMPLETED) {
            throw new RuntimeException("Completed ride cannot be cancelled");
        }

        ride.setStatus(RideStatus.CANCELLED);

        Driver driver = driverRepository.findById(ride.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driver.setAvailable(true);

        driverRepository.save(driver);

        Ride updatedRide = rideRepository.save(ride);

        RideResponseDTO response = new RideResponseDTO();

        response.setRideId(updatedRide.getId());
        response.setUserId(updatedRide.getUserId());
        response.setDriverId(updatedRide.getDriverId());
        response.setStatus(updatedRide.getStatus());
        response.setEstimatedDistance(updatedRide.getEstimatedDistance());
        response.setEstimatedFare(updatedRide.getEstimatedFare());
        response.setMessage("Ride cancelled successfully");

        return response;

    }
    public List<Ride> getRideHistory(Long userId) {
        return rideRepository.findByUserId(userId);
    }
}