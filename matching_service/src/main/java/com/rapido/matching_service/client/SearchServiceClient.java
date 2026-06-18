package com.rapido.matching_service.client;

import com.rapido.matching_service.dto.DriverDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "search-service")
public interface SearchServiceClient {

    @GetMapping("/search/drivers/nearby")
    List<DriverDto> getNearbyDrivers(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam String radius,
            @RequestParam String vehicleType
    );
}