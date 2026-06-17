package com.rapido.search_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.rapido.search_service.repository.DriverRepository;
import com.rapido.search_service.document.DriverDocument;


@Service
@RequiredArgsConstructor
public class DriverSearchService {

    private final DriverRepository repository;

    public Iterable<DriverDocument> getAllDrivers() {
        return repository.findAll();
    }
}