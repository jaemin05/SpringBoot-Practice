package com.example.fcmpractice.domain.notification.service;

import com.example.fcmpractice.domain.notification.domain.repository.NotificationRepository;
import com.example.fcmpractice.domain.notification.presentation.dto.NotificationListResponse;
import com.example.fcmpractice.domain.notification.presentation.dto.NotificationListResponse.NotificationResponse;
import com.example.fcmpractice.domain.user.domain.User;
import com.example.fcmpractice.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService{

    private final UserFacade userFacade;
    private final NotificationRepository notificationRepository;

    @Override
    @Transactional(readOnly = true)
    public NotificationListResponse getNotification(Pageable pageable) {

        User user = userFacade.getCurrentUser();

        List<NotificationResponse> notificationResponseList = notificationRepository.findByUser(user, pageable)
                .stream()
                .map(notification -> NotificationResponse.builder()
                        .id(notification.getNotification().getId())
                        .title(notification.getNotification().getTitle())
                        .content(notification.getNotification().getContent())
                        .data(notification.getNotification().getData())
                        .build())
                .collect(Collectors.toList());

        return NotificationListResponse.builder()
                .notificationResponseList(notificationResponseList).build();
    }
}
