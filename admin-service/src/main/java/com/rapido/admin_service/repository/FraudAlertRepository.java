package com.rapido.admin_service.repository;

import com.rapido.admin_service.entity.FraudAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FraudAlertRepository extends JpaRepository<FraudAlert, Long> {

    List<FraudAlert> findBySeverity(String severity);

    List<FraudAlert> findByResolved(Boolean resolved);
}