package com.rapido.driver_service.repository;

import com.rapido.driver_service.entity.Driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    boolean existsByEmail(String email);

    Optional<Driver> findByEmail(String email);

    List<Driver> findByAvailableTrueAndOnlineTrue();
}