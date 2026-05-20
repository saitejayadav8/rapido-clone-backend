package com.rapido.notification_service.controller;

import com.rapido.notification_service.dto.NotificationRequestDTO;
import com.rapido.notification_service.entity.Notification;
import com.rapido.notification_service.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Notification Service Working");
    }

    @PostMapping("/send")
    public ResponseEntity<Notification> sendNotification(
            @Valid @RequestBody NotificationRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(notificationService.sendNotification(requestDTO));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getNotificationsByUser(userId));
    }

    @GetMapping("/recipient")
    public ResponseEntity<List<Notification>> getByRecipient(@RequestParam String recipient) {
        return ResponseEntity.ok(notificationService.getNotificationsByRecipient(recipient));
    }
}