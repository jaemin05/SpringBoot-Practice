package com.example.fcmpractice.infrastructure;

import com.example.fcmpractice.domain.notice.domain.Notice;
import com.example.fcmpractice.infrastructure.dto.NotificationRequest;

public interface FcmUtil {

    void initialize();

    void sendNotification(NotificationRequest request);

    void sendNoticeNotification(Notice notice);
}
