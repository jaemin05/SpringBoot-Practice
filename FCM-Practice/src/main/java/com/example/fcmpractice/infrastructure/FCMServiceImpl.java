package com.example.fcmpractice.infrastructure;

import com.example.fcmpractice.domain.notification.domain.Notification;
import com.example.fcmpractice.domain.notification.domain.repository.NotificationRepository;
import com.example.fcmpractice.infrastructure.dto.NotificationRequest;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.MulticastMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Component
public class FCMServiceImpl implements FCMService {

    @Value("${firebase.path}")
    private String path;

    private final NotificationRepository notificationRepository;

    private static final String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
    private static final String[] SCOPES = {MESSAGING_SCOPE};

    @PostConstruct
    @Override
    public void initialize() {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            InputStream serviceAccount = resource.getInputStream();

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount)
                            .createScoped(Arrays.asList(SCOPES)))
                    .build();

            FirebaseApp.initializeApp(options);
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
                        .build());

        MulticastMessage message = MulticastMessage.builder()
                .putData("title", request.getTitle())
                .putData("content", request.getContent())
                .addAllTokens()
    }


}
