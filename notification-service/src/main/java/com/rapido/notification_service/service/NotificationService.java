package com.rapido.notification_service.service;

import com.rapido.notification_service.dto.NotificationRequestDTO;
import com.rapido.notification_service.entity.Notification;
import com.rapido.notification_service.entity.NotificationStatus;
import com.rapido.notification_service.entity.NotificationType;
import com.rapido.notification_service.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification sendNotification(NotificationRequestDTO requestDTO) {

        Notification notification = new Notification();

        notification.setUserId(requestDTO.getUserId());

        notification.setRecipient(requestDTO.getRecipient());

        notification.setTitle(requestDTO.getTitle());

        notification.setMessage(requestDTO.getMessage());

        notification.setType(requestDTO.getType());

        notification.setCreatedAt(LocalDateTime.now());

        if (requestDTO.getType() == NotificationType.EMAIL) {

            notification.setStatus(NotificationStatus.EMAIL_SENT);

        } else if (requestDTO.getType() == NotificationType.SMS) {

            notification.setStatus(NotificationStatus.SMS_SENT);

        } else if (requestDTO.getType() == NotificationType.PUSH) {

            notification.setStatus(NotificationStatus.PUSH_SENT);

        } else {

            notification.setStatus(NotificationStatus.FAILED);
        }

        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByUser(Long userId) {

        return notificationRepository.findByUserId(userId);
    }

    public List<Notification> getNotificationsByRecipient(String recipient) {

        return notificationRepository.findByRecipient(recipient);
    }
}