package com.example.fcmpractice.domain.notification.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationListId implements Serializable {

    private Long notification;

    private Long user;
}
