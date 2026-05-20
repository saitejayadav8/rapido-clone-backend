package com.rapido.driver_service.controller;

import com.rapido.driver_service.dto.DriverRequest;
import com.rapido.driver_service.entity.Driver;
import com.rapido.driver_service.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Driver Service Working");
    }

    @PostMapping("/profile")
    public ResponseEntity<Driver> createDriver(
            @Valid @RequestBody DriverRequest request
    ) {
        return ResponseEntity.ok(driverService.createDriver(request));
    }

    @GetMapping("/profile/{email}")
    public ResponseEntity<Driver> getDriverByEmail(
            @PathVariable String email
    ) {
        return ResponseEntity.ok(driverService.getDriverByEmail(email));
    }

    @PutMapping("/availability/{email}")
    public ResponseEntity<Driver> updateAvailability(
            @PathVariable String email,
            @RequestParam Boolean available
    ) {
        return ResponseEntity.ok(
                driverService.updateAvailability(email, available)
        );
    }

    @PutMapping("/online/{email}")
    public ResponseEntity<Driver> updateOnlineStatus(
            @PathVariable String email,
            @RequestParam Boolean online
    ) {
        return ResponseEntity.ok(
                driverService.updateOnlineStatus(email, online)
        );
    }

    @GetMapping("/available")
    public ResponseEntity<List<Driver>> getAvailableDrivers() {
        return ResponseEntity.ok(driverService.getAvailableDrivers());
    }
}