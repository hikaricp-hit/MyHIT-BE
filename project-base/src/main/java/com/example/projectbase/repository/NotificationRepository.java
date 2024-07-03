package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository <Notification, String> {
    Notification findNotificationByName(String name);

    Page<Notification> findAllNotificationsByType(String type, org.springframework.data.domain.Pageable pageable);
}
