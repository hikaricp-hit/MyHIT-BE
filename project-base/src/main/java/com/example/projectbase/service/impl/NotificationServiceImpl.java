package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.MessageConstrant;
import com.example.projectbase.constant.NotificationConstrant;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.pagination.PagingMeta;
import com.example.projectbase.domain.dto.request.NotificationCreateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.NotificationDto;
import com.example.projectbase.domain.entity.Member_Notification;
import com.example.projectbase.domain.entity.Notification;
import com.example.projectbase.domain.mapper.NotificationMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.NotificationRepository;
import com.example.projectbase.service.NotificationService;
import com.example.projectbase.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    private final Member_NotificationServiceImpl member_notificationService;

    @Override
    public NotificationDto createGeneralNotification(NotificationCreateDto notificationCreateDto) {
        Notification notification = notificationMapper.toNotification(notificationCreateDto);
        notification.setType(NotificationConstrant.TYPE_GENERAL);
        notificationRepository.save(notification);
        return notificationMapper.toDto(notification);
    }

    @Override
    public NotificationDto createPersonalNotification(NotificationCreateDto notificationCreateDto, String memberId) {
        Notification notification = notificationMapper.toNotification(notificationCreateDto);
        notification.setType(NotificationConstrant.TYPE_PERSONAL);
        notificationRepository.save(notification);
        member_notificationService.createConnect(notification,memberId);
        return null;
    }

    @Override
    public PaginationResponseDto<NotificationDto> getGeneralNotification(PaginationFullRequestDto paginationRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationRequestDto);
        Page<Notification> notesPage = notificationRepository.findAllNotificationsByType(NotificationConstrant.TYPE_GENERAL,pageable);
        PagingMeta pagingMeta = new PagingMeta(
                notesPage.getTotalElements(),
                notesPage.getTotalPages(),
                notesPage.getNumber(),
                notesPage.getSize(),
                paginationRequestDto.getSortBy(),
                paginationRequestDto.getIsAscending().toString()
        );
        List<NotificationDto> notificationDtoList= notesPage.stream().map(notificationMapper::toDto).collect(Collectors.toList());
        return new PaginationResponseDto<>(pagingMeta,notificationDtoList);
    }

    @Override
    public PaginationResponseDto<NotificationDto> getPersonalNotification(String memberId, PaginationFullRequestDto paginationRequestDto) {
        List<Member_Notification> list= member_notificationService.findByMemberId(memberId);
        List<NotificationDto> res= new ArrayList<>();
        for(Member_Notification member_notification:list){
            Notification notification = notificationRepository.findById(member_notification.getNotification().getId()).get();
            res.add(notificationMapper.toDto(notification));
        }
        Pageable pageable = PaginationUtil.buildPageable(paginationRequestDto);
        Page<NotificationDto> notesPage = convertListToPage(res, pageable);
        PagingMeta pagingMeta = new PagingMeta(
                notesPage.getTotalElements(),
                notesPage.getTotalPages(),
                notesPage.getNumber(),
                notesPage.getSize(),
                paginationRequestDto.getSortBy(),
                paginationRequestDto.getIsAscending().toString()
        );
        List<NotificationDto> notificationDtoList= notesPage.toList();
        return new PaginationResponseDto<>(pagingMeta,notificationDtoList);
    }

    @Override
    public Notification findNotificationById(String notificationId) throws Exception {
        return notificationRepository.findById(notificationId).orElseThrow(() ->
                new NotFoundException(ErrorMessage.Notification.ERR_NOT_FOUND_ID, new String[]{notificationId}));
    }

    @Override
    public NotificationDto updateNotification(String notificationId, NotificationCreateDto notificationCreateDto) {
        Optional<Notification> notification= Optional.ofNullable(notificationRepository.findById(notificationId).orElseThrow(() ->
                new NotFoundException(ErrorMessage.Notification.ERR_NOT_FOUND_ID, new String[]{notificationId})));
        notification.get().setName(notificationCreateDto.getName());
        notification.get().setDetail(notificationCreateDto.getDetail());
        notification.get().setSendDate(notificationCreateDto.getSendDate());
        notificationRepository.save(notification.get());
        return notificationMapper.toDto(notification.get());
    }

    @Override
    public CommonResponseDto deleteNotification(String notificationId) {
        Optional<Notification> notification= Optional.ofNullable(notificationRepository.findById(notificationId).orElseThrow(() ->
                new NotFoundException(ErrorMessage.Notification.ERR_NOT_FOUND_ID, new String[]{notificationId})));
        notificationRepository.deleteById(notification.get().getId());
        return  new CommonResponseDto(true, MessageConstrant.SUCCESS);
    }

    public static <T> Page<T> convertListToPage(List<T> list, Pageable pageable) {
        int totalElements = list.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), totalElements);
        List<T> subList = list.subList(start, end);
        return new PageImpl<>(subList, pageable, totalElements);
    }
}
