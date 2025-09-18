package com.lgcns.studify_be.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.lgcns.studify_be.notification.domain.entity.NotificationEntity;
import com.lgcns.studify_be.notification.repository.NotificationRepository;
import com.lgcns.studify_be.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
    
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    private NotificationRepository notificationRepository;

    public void sendNotification(User user, String message) {
        // DB에 저장
        NotificationEntity notification = NotificationEntity.builder()
                .user(user)
                .message(message)
                .build();
        notificationRepository.save(notification);

        // WebSocket 전송
        messagingTemplate.convertAndSend(
                "/topic/notifications/" + user.getId(),
                message
        );
    }



}
