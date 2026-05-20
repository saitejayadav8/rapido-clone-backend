package com.rapido.notification_service.repository;

import com.rapido.notification_service.entity.Notification;
import com.rapido.notification_service.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserId(Long userId);

    List<Notification> findByRecipient(String recipient);

    List<Notification> findByType(NotificationType type);

    void deleteByCreatedAtBefore(LocalDateTime time);
}