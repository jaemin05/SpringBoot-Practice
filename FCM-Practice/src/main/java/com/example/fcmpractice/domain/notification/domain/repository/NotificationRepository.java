package com.example.fcmpractice.domain.notification.domain.repository;

import com.example.fcmpractice.domain.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
