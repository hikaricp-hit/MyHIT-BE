package com.example.projectbase.service;

import com.example.projectbase.domain.entity.Member_Notification;

import java.util.List;

public interface Member_NotificationService {
    Member_Notification createConnect(String notificationId, String memberId);
    List<Member_Notification> findByMemberId(String memberId);
    Member_Notification updateNotification(String member_notificationId,String notificationId, String memberId);
    Member_Notification deleteNotification(String notificationId);

}
