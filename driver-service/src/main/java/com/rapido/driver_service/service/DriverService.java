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

    public Driver createDriver(DriverRequest request) {

        if (driverRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Driver already exists");
        }

        Driver driver = new Driver();

        driver.setFullName(request.getFullName());
        driver.setEmail(request.getEmail());
        driver.setPhone(request.getPhone());
        driver.setVehicleModel(request.getVehicleModel());
        driver.setVehicleNumber(request.getVehicleNumber());

        driver.setAvailable(true);
        driver.setOnline(true);

        return driverRepository.save(driver);
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriverByEmail(String email) {

        return driverRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
    }

    public Driver updateAvailability(String email, Boolean available) {

        Driver driver = getDriverByEmail(email);

        driver.setAvailable(available);

        return driverRepository.save(driver);
    }

    public Driver updateOnlineStatus(String email, Boolean online) {

        Driver driver = getDriverByEmail(email);

        driver.setOnline(online);

        return driverRepository.save(driver);
    }

    public List<Driver> getAvailableDrivers() {

        return driverRepository.findByAvailableTrueAndOnlineTrue();
    }
}