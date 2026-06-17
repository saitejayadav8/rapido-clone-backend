package com.rapido.search_service.service;

import com.rapido.search_service.document.DriverDocument;
import com.rapido.search_service.kafka.DriverUpdatedEvent;
import com.rapido.search_service.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverIndexService {

    private final DriverRepository repository;

    public void indexDriver(DriverUpdatedEvent event) {

        DriverDocument document =
                DriverDocument.builder()
                        .driverId(event.getDriverId())
                        .name(event.getName())
                        .rating(event.getRating())
                        .vehicleType(event.getVehicleType())
                        .location(
                                new GeoPoint(
                                        event.getLatitude(),
                                        event.getLongitude()
                                )
                        )
                        .build();

        repository.save(document);
    }
}