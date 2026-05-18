package com.rapido.ride_service.algorithm;

import com.rapido.ride_service.entity.Driver;
import com.rapido.ride_service.util.DistanceUtil;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class DriverMatchingAlgorithm {

    public Driver findNearestDriver(
            List<Driver> drivers,
            Double pickupLatitude,
            Double pickupLongitude
    ) {
        return drivers.stream()
                .filter(driver -> driver.getCurrentLatitude() != null)
                .filter(driver -> driver.getCurrentLongitude() != null)
                .min(Comparator.comparingDouble(driver ->
                        DistanceUtil.calculateDistance(
                                pickupLatitude,
                                pickupLongitude,
                                driver.getCurrentLatitude(),
                                driver.getCurrentLongitude()
                        )
                ))
                .orElseThrow(() -> new RuntimeException("No nearby drivers available"));
    }
}