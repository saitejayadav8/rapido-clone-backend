package com.rapido.search_service.repository;

import com.rapido.search_service.document.DriverDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DriverRepository
        extends ElasticsearchRepository<DriverDocument, Long> {
}