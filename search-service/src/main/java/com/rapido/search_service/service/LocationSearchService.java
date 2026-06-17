package com.rapido.search_service.service;

import com.rapido.search_service.document.LocationDocument;
import com.rapido.search_service.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationSearchService {

    private final LocationRepository repository;

    public Iterable<LocationDocument> getAllLocations() {
        return repository.findAll();
    }
}