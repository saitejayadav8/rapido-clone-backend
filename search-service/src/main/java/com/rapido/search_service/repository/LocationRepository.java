package com.rapido.search_service.repository;

import com.rapido.search_service.document.LocationDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface LocationRepository
        extends ElasticsearchRepository<LocationDocument, Long> {

    List<LocationDocument> findByNameContainingIgnoreCase(
            String keyword
    );
}