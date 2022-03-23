package com.example.fcmpractice.infrastructure;

import com.example.fcmpractice.domain.notice.domain.Notice;
import com.example.fcmpractice.domain.notification.domain.Notification;
import com.example.fcmpractice.domain.notification.domain.repository.NotificationRepository;
import com.example.fcmpractice.infrastructure.dto.NotificationRequest;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Component
public class FcmNotification implements FcmUtil {

    @Value("${firebase.path}")
    private String path;

    private final NotificationRepository notificationRepository;

    private static final String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
    private static final String[] SCOPES = {MESSAGING_SCOPE};

    @PostConstruct
    @Override
    public void initialize() {
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(path).getInputStream())
                            .createScoped(Arrays.asList(SCOPES)))
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.error("Firebase application has been initialized");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void sendNotification(NotificationRequest request) {
        Notification notification = notificationRepository.save(
                Notification.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .data(request.getData())
                        .build());

        Message message = Message.builder()
                .putData("notification-id", notification.getId().toString())
                .setToken(request.getUser().getDeviceToken())
                .setNotification(
                        com.google.firebase.messaging.Notification.builder()
                                .setTitle(request.getTitle())
                                .setBody(request.getContent())
                                .build())
                .setAndroidConfig(AndroidConfig.builder()
                        .setTtl(3600 * 1000)
                        .setNotification(AndroidNotification.builder()
                                .setIcon("stock_ticker_update")
                                .setColor("#f45342")
                                .build())
                        .build())
                .setApnsConfig(ApnsConfig.builder()
                        .setAps(Aps.builder()
                                .setBadge(42)
                                .build())
                        .build())
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }

    @Override
    public void sendNoticeNotification(Notice notice) {
        sendNotification(
                NotificationRequest.builder()
                        .user(notice.getUser())
                        .title(notice.getTitle())
                        .content(notice.getContent())
                        .data(notice.getId())
                        .build());
    }
}
