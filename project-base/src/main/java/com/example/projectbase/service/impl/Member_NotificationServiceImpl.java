package com.example.projectbase.service.impl;

import com.example.projectbase.domain.entity.Member_Notification;
import com.example.projectbase.repository.Member_NotificationRepository;
import com.example.projectbase.service.MemberService;
import com.example.projectbase.service.Member_NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class Member_NotificationServiceImpl implements Member_NotificationService {
    private Member_NotificationRepository member_notificationRepository;
    private NotificationServiceImpl notificationService;
    private MemberServiceImpl memberService;

    @Override
    public Member_Notification createConnect(String notificationId, String memberId) {
        Member_Notification connect = new Member_Notification();
        try {
            connect.setNotification(notificationService.findNotificationById(notificationId));
            connect.setNotifiedMember(memberService.findMemberById(memberId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return member_notificationRepository.save(connect);
    }

    @Override
    public List<Member_Notification> findByMemberId(String memberId) {
        Optional<List<Member_Notification>> list= member_notificationRepository.findByMemberId(memberId);
        return list.orElse(new ArrayList<Member_Notification>());
    }

    @Override
    public Member_Notification updateNotification(String member_notificationId, String notificationId, String memberId) {
        return null;
    }

    @Override
    public Member_Notification deleteNotification(String notificationId) {
        return null;
    }
}
