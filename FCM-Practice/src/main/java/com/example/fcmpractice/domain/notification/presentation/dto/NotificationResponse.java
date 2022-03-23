package com.example.fcmpractice.domain.notification.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NotificationResponse {

    private final List<NotificationResponse> notificationResponseList;

    @Getter
    @Builder
    public static class NotificationResponse {
        private final Long id;
        private final String title;
        private final String content;
        private final Long data;
    }

}
