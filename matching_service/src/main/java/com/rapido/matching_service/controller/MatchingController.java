package com.rapido.matching_service.controller;

import com.rapido.matching_service.dto.*;
import com.rapido.matching_service.service.MatchingService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matching")
@RequiredArgsConstructor
public class MatchingController {

    private final MatchingService matchingService;

    @PostMapping("/find-driver")
    public DriverMatchResponse findDriver(
            @RequestBody RideRequestDto request
    ) {

        return matchingService.findDriver(
                request
        );
    }
}