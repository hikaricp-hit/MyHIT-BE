package com.example.projectbase.service;

import com.example.projectbase.domain.entity.Member_Notification;
import com.example.projectbase.domain.entity.Notification;

import java.util.List;

public interface Member_NotificationService {
    Member_Notification createConnect(Notification notification, String memberId);
    List<Member_Notification> findByMemberId(String memberId);
    Member_Notification updateNotification(String member_notificationId,String notificationId, String memberId);
    Member_Notification deleteNotification(String notificationId);

}
