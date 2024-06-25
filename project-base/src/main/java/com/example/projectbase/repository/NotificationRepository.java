package com.example.projectbase.repository;

import com.example.projectbase.domain.dto.response.NotificationDto;
import com.example.projectbase.domain.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository <Notification, String> {
    List<NotificationDto> findAllNotificationsByType(String type);
}
