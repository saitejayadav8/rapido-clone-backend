package com.rapido.auth_service.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @GetMapping("/{email}/rides")
    @PreAuthorize("#email == authentication.name || hasRole('SUPER_ADMIN')")
    public String getDriverRides(
            @PathVariable String email) {

        return "Rides for driver: " + email;
    }
}