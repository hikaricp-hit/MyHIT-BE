package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.domain.dto.request.NotificationCreateDto;
import com.example.projectbase.domain.dto.response.NotificationDto;
import com.example.projectbase.domain.entity.Member_Notification;
import com.example.projectbase.domain.entity.Notification;
import com.example.projectbase.domain.mapper.NotificationMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.NotificationRepository;
import com.example.projectbase.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<NotificationDto> getGeneralNotification(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Notification> notesPage = notificationRepository.findAllNotificationsByType("general",pageable);
        return notesPage.stream().map(notificationMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<NotificationDto> getPersonalNotification(String memberId, int page, int size) {
        List<Member_Notification> list= member_notificationService.findByMemberId(memberId);
        List<NotificationDto> res= new ArrayList<>();
        for(Member_Notification member_notification:list){
            Notification notification = notificationRepository.findById(member_notification.getId()).get();
            res.add(notificationMapper.toDto(notification));
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<NotificationDto> notesPage = convertListToPage(res, pageable);
        return res;
    }

    @Override
    public Notification findNotificationById(String notificationId) throws Exception {
        return notificationRepository.findById(notificationId).orElseThrow(() ->
                new NotFoundException(ErrorMessage.Notification.ERR_NOT_FOUND_ID, new String[]{notificationId}));
    }

    @Override
    public NotificationDto updateNotification(String notificaitonName, NotificationCreateDto notificationCreateDto) {
        Optional<Notification> notification= Optional.ofNullable(notificationRepository.findNotificationByName(notificaitonName));
        notification.get().setName(notificationCreateDto.getName());
        notification.get().setDetail(notificationCreateDto.getDetail());
        notification.get().setSendDate(notificationCreateDto.getSendDate());
        notificationRepository.save(notification.get());
        return notificationMapper.toDto(notification.get());
    }

    @Override
    public void deleteNotification(String notificationName) {
        Optional<Notification> notification= Optional.ofNullable(notificationRepository.findNotificationByName(notificationName));
        notificationRepository.deleteById(notification.get().getId());
    }

    public static <T> Page<T> convertListToPage(List<T> list, Pageable pageable) {
        int totalElements = list.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), totalElements);

        List<T> subList = list.subList(start, end);
        return new PageImpl<>(subList, pageable, totalElements);
    }
}
