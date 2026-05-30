package com.rapido.admin_service.service;

import com.rapido.admin_service.dto.FraudSimulationRequestDTO;
import com.rapido.admin_service.entity.FraudAlert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FraudDetectionService {

    private final FraudAlertService fraudAlertService;
    private final AuditLogService auditLogService;

    public FraudAlert simulateFraud(FraudSimulationRequestDTO request) {

        FraudAlert alert = FraudAlert.builder()
                .userId(request.getUserId())
                .fraudType(request.getFraudType())
                .description(request.getDescription())
                .severity(request.getSeverity())
                .build();

        FraudAlert savedAlert = fraudAlertService.createFraudAlert(alert);

        auditLogService.saveLog(
                "admin@rapido.com",
                "FRAUD_DETECTED",
                "Fraud detected: " + request.getFraudType()
        );

        return savedAlert;
    }
}