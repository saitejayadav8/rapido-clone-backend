package com.rapido.search_service.service;

import com.rapido.search_service.document.DriverDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeoSearchService {

    private final ElasticsearchOperations operations;
    private final DriverRankingService rankingService;

    public List<DriverDocument> findNearbyDrivers(
            double lat,
            double lon,
            String radius
    ) {

        NativeQuery query =
                NativeQuery.builder()
                        .withQuery(q -> q
                                .geoDistance(g -> g
                                        .field("location")
                                        .distance(radius)
                                        .location(l -> l
                                                .latlon(ll -> ll
                                                        .lat(lat)
                                                        .lon(lon)
                                                )
                                        )
                                )
                        )
                        .build();

        return operations.search(
                        query,
                        DriverDocument.class
                )
                .stream()
                .map(SearchHit::getContent)
                .sorted(
                        Comparator.comparingDouble(
                                rankingService::calculateScore
                        ).reversed()
                )
                .toList();
    }
}