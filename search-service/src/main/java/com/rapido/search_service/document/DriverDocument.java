package com.rapido.search_service.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "drivers")
public class DriverDocument {

    @Id
    private Long driverId;

    private String name;

    private Double rating;

    private String vehicleType;

    private GeoPoint location;

    // Ranking fields
    private Boolean available;

    private Integer completedRides;

    private Double acceptanceRate;
}