package com.rapido.auth_service.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/support")
public class SupportController {

    @GetMapping("/tickets")
    @PreAuthorize("hasAnyRole('SUPPORT_ADMIN','SUPER_ADMIN')")
    public String tickets() {
        return "Support Tickets";
    }
}