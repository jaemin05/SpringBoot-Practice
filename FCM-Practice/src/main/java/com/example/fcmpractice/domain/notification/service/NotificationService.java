package com.example.fcmpractice.domain.notification.service;

import com.example.fcmpractice.domain.notification.presentation.dto.NotificationListResponse;
import org.springframework.data.domain.Pageable;


public interface NotificationService {

    NotificationListResponse getNotification(Pageable pageable);
}
