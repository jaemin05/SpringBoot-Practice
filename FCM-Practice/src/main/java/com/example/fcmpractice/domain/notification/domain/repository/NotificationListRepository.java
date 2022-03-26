package com.example.fcmpractice.domain.notification.domain.repository;

import com.example.fcmpractice.domain.notification.domain.Notification;
import com.example.fcmpractice.domain.notification.domain.NotificationList;
import com.example.fcmpractice.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface NotificationListRepository extends CrudRepository<NotificationList, Notification> {
    Page<NotificationList> findByUser(User user, Pageable pageable);
}
