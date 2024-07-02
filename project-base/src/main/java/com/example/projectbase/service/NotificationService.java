package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.NotificationCreateDto;
import com.example.projectbase.domain.dto.response.NotificationDto;
import com.example.projectbase.domain.entity.Notification;

import java.util.List;

public interface NotificationService {
    NotificationDto createGeneralNotification(NotificationCreateDto notificationCreateDto);
    NotificationDto createPersonalNotification(NotificationCreateDto notificationCreateDto, String memberId);
    List<NotificationDto> getGeneralNotification(int page, int size);
    List<NotificationDto> getPersonalNotification(String memberId, int page, int size);
    Notification findNotificationById(String notificationId) throws Exception;
    NotificationDto updateNotification(String notificationName, NotificationCreateDto notificationCreateDto);
    void deleteNotification(String notificationName);
}
