package com.example.fcmpractice.domain.notification.exception;

import com.example.fcmpractice.global.error.ErrorCode;
import com.example.fcmpractice.global.error.FCMPracticeException;

public class NotificationNotFoundException extends FCMPracticeException {
    public static NotificationNotFoundException EXCEPTION =
            new NotificationNotFoundException();

    private NotificationNotFoundException() {
        super(ErrorCode.NOTIFICATION_NOT_FOUND);
    }
}
