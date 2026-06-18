package com.rapido.matching_service.service;

import com.rapido.matching_service.client.SearchServiceClient;
import com.rapido.matching_service.dto.*;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchingService {

    private final SearchServiceClient searchClient;

    public DriverMatchResponse findDriver(
            RideRequestDto request
    ) {

        List<DriverDto> drivers =
                searchClient.getNearbyDrivers(
                        request.getPickupLat(),
                        request.getPickupLon(),
                        "5km",
                        request.getVehicleType()
                );

        if (drivers.isEmpty()) {

            return DriverMatchResponse.builder()
                    .rideId(request.getRideId())
                    .status("NO_DRIVER_FOUND")
                    .build();
        }

        DriverDto driver = drivers.get(0);

        return DriverMatchResponse.builder()
                .rideId(request.getRideId())
                .driverId(driver.getDriverId())
                .driverName(driver.getName())
                .status("DRIVER_ASSIGNED")
                .build();
    }
}