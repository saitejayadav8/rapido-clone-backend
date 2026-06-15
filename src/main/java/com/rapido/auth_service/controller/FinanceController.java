package com.rapido.auth_service.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/finance")
public class FinanceController {

    @GetMapping("/payments")
    @PreAuthorize("hasAnyRole('FINANCE_ADMIN','SUPER_ADMIN')")
    public String payments() {

        return "Payment Records";
    }
}