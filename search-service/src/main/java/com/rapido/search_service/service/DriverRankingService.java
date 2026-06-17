package com.rapido.search_service.service;

import com.rapido.search_service.document.DriverDocument;
import org.springframework.stereotype.Service;

@Service
public class DriverRankingService {

    public double calculateScore(DriverDocument driver) {

        double ratingScore =
                driver.getRating() != null
                        ? driver.getRating() * 20
                        : 0;

        double rideScore =
                driver.getCompletedRides() != null
                        ? driver.getCompletedRides() * 0.1
                        : 0;

        double acceptanceScore =
                driver.getAcceptanceRate() != null
                        ? driver.getAcceptanceRate()
                        : 0;

        return ratingScore
                + rideScore
                + acceptanceScore;
    }
}