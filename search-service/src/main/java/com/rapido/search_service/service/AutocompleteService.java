package com.rapido.search_service.service;

import com.rapido.search_service.document.LocationDocument;
import com.rapido.search_service.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutocompleteService {

    private final LocationRepository repository;

    public List<LocationDocument> search(String keyword) {

        return repository.findByNameContainingIgnoreCase(
                keyword
        );
    }
}