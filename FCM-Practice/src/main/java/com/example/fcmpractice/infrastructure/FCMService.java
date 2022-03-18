package com.example.fcmpractice.infrastructure;

import com.example.fcmpractice.infrastructure.dto.NotificationRequest;

public interface FCMService {

    public void initialize();

    public void sendNotification(NotificationRequest request);
}
