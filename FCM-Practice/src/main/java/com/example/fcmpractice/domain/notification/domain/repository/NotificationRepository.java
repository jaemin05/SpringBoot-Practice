package com.example.fcmpractice.domain.notification.domain.repository;

import com.example.fcmpractice.domain.notification.domain.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
}
