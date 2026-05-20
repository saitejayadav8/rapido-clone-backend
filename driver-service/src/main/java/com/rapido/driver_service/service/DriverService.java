package com.rapido.driver_service.service;

import com.rapido.driver_service.dto.DriverRequest;
import com.rapido.driver_service.entity.Driver;
import com.rapido.driver_service.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    // Create Driver Profile
    public Driver createDriver(DriverRequest request) {

        if (driverRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Driver already exists with this email");
        }

        Driver driver = new Driver();

        driver.setFullName(request.getFullName());
        driver.setEmail(request.getEmail());
        driver.setPhone(request.getPhone());
        driver.setVehicleNumber(request.getVehicleNumber());
        driver.setVehicleModel(request.getVehicleModel());

        driver.setAvailable(true);
        driver.setOnline(true);

        return driverRepository.save(driver);
    }

    // Get Driver By Email
    public Driver getDriverByEmail(String email) {

        return driverRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Driver not found"));
    }

    // Update Driver Availability
    public Driver updateAvailability(String email,
                                     Boolean available) {

        Driver driver = driverRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Driver not found"));

        driver.setAvailable(available);

        return driverRepository.save(driver);
    }

    // Update Driver Online Status
    public Driver updateOnlineStatus(String email,
                                     Boolean online) {

        Driver driver = driverRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Driver not found"));

        driver.setOnline(online);

        return driverRepository.save(driver);
    }

    // Get All Available Drivers
    public List<Driver> getAvailableDrivers() {

        return driverRepository
                .findByAvailableTrueAndOnlineTrue();
    }
}