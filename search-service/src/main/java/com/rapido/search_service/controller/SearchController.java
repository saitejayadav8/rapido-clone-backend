package com.rapido.search_service.controller;

import com.rapido.search_service.document.DriverDocument;
import com.rapido.search_service.document.LocationDocument;
import com.rapido.search_service.service.AutocompleteService;
import com.rapido.search_service.service.DriverSearchService;
import com.rapido.search_service.service.GeoSearchService;
import com.rapido.search_service.service.LocationSearchService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final DriverSearchService service;
    private final GeoSearchService geoSearchService;
    private final LocationSearchService locationSearchService;
    private final AutocompleteService autocompleteService;

    /**
     * Get all drivers from Elasticsearch
     */
    @GetMapping("/drivers")
    public Iterable<DriverDocument> getDrivers() {
        return service.getAllDrivers();
    }

    /**
     * Nearby driver discovery
     *
     * Example:
     * /search/drivers/nearby?lat=17.385&lon=78.486&radius=5km
     *
     * Example:
     * /search/drivers/nearby?lat=17.385&lon=78.486&radius=5km&vehicleType=Bike
     */
    @GetMapping("/drivers/nearby")
    public List<DriverDocument> nearbyDrivers(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam String radius,
            @RequestParam(required = false) String vehicleType
    ) {

        List<DriverDocument> drivers =
                geoSearchService.findNearbyDrivers(
                        lat,
                        lon,
                        radius
                );

        if (vehicleType != null && !vehicleType.isBlank()) {

            drivers = drivers.stream()
                    .filter(driver ->
                            vehicleType.equalsIgnoreCase(
                                    driver.getVehicleType()
                            )
                    )
                    .toList();
        }

        return drivers;
    }

    /**
     * Get all locations from Elasticsearch
     */
    @GetMapping("/locations")
    public Iterable<LocationDocument> getLocations() {
        return locationSearchService.getAllLocations();
    }

    /**
     * Location autocomplete search
     *
     * Example:
     * /search/autocomplete?keyword=Hyd
     */
    @GetMapping("/autocomplete")
    public List<LocationDocument> autocomplete(
            @RequestParam String keyword
    ) {

        return autocompleteService.search(
                keyword
        );
    }
}