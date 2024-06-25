package com.example.projectbase.service.impl;

import com.example.projectbase.domain.dto.request.NotificationCreateDto;
import com.example.projectbase.domain.dto.response.NotificationDto;
import com.example.projectbase.domain.entity.Member_Notification;
import com.example.projectbase.domain.entity.Notification;
import com.example.projectbase.domain.mapper.NotificationMapper;
import com.example.projectbase.repository.NotificationRepository;
import com.example.projectbase.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private Member_NotificationServiceImpl member_notificationService;

    @Override
    public NotificationDto createGeneralNotification(NotificationCreateDto notificationCreateDto) {
        Notification notification = notificationMapper.toNotification(notificationCreateDto);
        notification.setType("general");
        notificationRepository.save(notification);
        return notificationMapper.toDto(notification);
    }

    @Override
    public NotificationDto createPersonalNotification(NotificationCreateDto notificationCreateDto, String memberId) {
        Notification notification = notificationMapper.toNotification(notificationCreateDto);
        notification.setType("personal");
        notificationRepository.save(notification);
        member_notificationService.createConnect(notification.getId(),memberId);
        return null;
    }

    @Override
    public List<NotificationDto> getGeneralNotification() {
        return notificationRepository.findAllNotificationsByType("general");
    }

    @Override
    public List<NotificationDto> getPersonalNotification(String memberId) {
        List<Member_Notification> list= member_notificationService.findByMemberId(memberId);
        List<NotificationDto> res= new ArrayList<>();
        for(Member_Notification member_notification:list){
            Notification notification = notificationRepository.findById(member_notification.getId()).get();
            res.add(notificationMapper.toDto(notification));
        }
        return res;
    }

    @Override
    public Notification findNotificationById(String notificationId) throws Exception {
        return notificationRepository.findById(notificationId).orElseThrow(() ->{
            return  new Exception("Not found");
        });
    }

    @Override
    public NotificationDto updateNotification(String notificaitonId, NotificationCreateDto notificationCreateDto) {
        Optional<Notification> notification= notificationRepository.findById(notificaitonId);
        notification.get().setName(notificationCreateDto.getName());
        notification.get().setDetail(notificationCreateDto.getDetail());
        notification.get().setSendDate(notificationCreateDto.getSendDate());
        notificationRepository.save(notification.get());
        return notificationMapper.toDto(notification.get());
    }

    @Override
    public void deleteNotification(String notificationId) {
        Optional<Notification> notification= notificationRepository.findById(notificationId);
        notificationRepository.deleteById(notificationId);
    }
}
