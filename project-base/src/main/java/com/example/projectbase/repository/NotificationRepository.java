package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationRepository extends JpaRepository <Notification, String> {
    Notification findNotificationByName(String name);

    Page<Notification> findAllNotificationsByType(String general, org.springframework.data.domain.Pageable pageable);
}
