package com.example.fcmpractice.infrastructure.dto;

import com.example.fcmpractice.domain.notice.domain.Notice;
import com.example.fcmpractice.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationRequest {

    private final User user;

    private final String title;

    private final String content;

    private final Long data;
}
