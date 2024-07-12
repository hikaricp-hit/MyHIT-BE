package com.example.projectbase.service;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.NotificationCreateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.NotificationDto;
import com.example.projectbase.domain.entity.Notification;

public interface NotificationService {
    NotificationDto createGeneralNotification(NotificationCreateDto notificationCreateDto);
    NotificationDto createPersonalNotification(NotificationCreateDto notificationCreateDto, String memberId);
    PaginationResponseDto<NotificationDto> getGeneralNotification(PaginationFullRequestDto paginationFullRequestDto);
    PaginationResponseDto<NotificationDto> getPersonalNotification(String memberId, PaginationFullRequestDto paginationFullRequestDto);
    Notification findNotificationById(String notificationId) throws Exception;
    NotificationDto updateNotification(String notificationId, NotificationCreateDto notificationCreateDto);
    CommonResponseDto deleteNotification(String notificationId);
}
