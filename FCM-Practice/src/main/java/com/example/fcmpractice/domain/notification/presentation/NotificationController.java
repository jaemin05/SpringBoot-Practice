package com.example.fcmpractice.domain.notification.presentation;

import com.example.fcmpractice.domain.notification.presentation.dto.NotificationListResponse;
import com.example.fcmpractice.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public NotificationListResponse getNotificationList(Pageable pageable) {
        return notificationService.getNotification(pageable);
    }
}
