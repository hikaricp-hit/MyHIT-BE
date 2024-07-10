package com.example.projectbase.service;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.EventRequestDTO;
import com.example.projectbase.domain.dto.response.EventResponseDto;

import java.util.Date;
import java.util.List;

public interface EventService {

    PaginationResponseDto<EventResponseDto> getAllEvents(PaginationFullRequestDto paginationFullRequestDto);

    PaginationResponseDto<EventResponseDto> getEventsByType(String type, PaginationFullRequestDto paginationFullRequestDto);

    PaginationResponseDto<EventResponseDto> getEventsByDate(Date date, PaginationFullRequestDto paginationFullRequestDto);

    EventResponseDto getEventById(String id);

    EventResponseDto createClassEvent(EventRequestDTO eventRequestDTO);

    EventResponseDto createActivityEvent(EventRequestDTO eventRequestDTO);

    EventResponseDto createOfflineEvent(EventRequestDTO eventRequestDTO);

    EventResponseDto updateEvent(String id, EventRequestDTO eventRequestDTO);

    boolean deleteEvent(String id);
}
