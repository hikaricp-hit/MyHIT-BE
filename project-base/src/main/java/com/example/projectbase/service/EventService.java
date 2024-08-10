package com.example.projectbase.service;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.EventResquestDTO;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.EventResponseDto;
import com.example.projectbase.domain.entity.Event;

import java.util.Date;
import java.util.List;

public interface EventService {
    EventResponseDto createClassEvent(EventResquestDTO eventRequestDTO);
    EventResponseDto createActivityEvent(EventResquestDTO eventRequestDTO);
    EventResponseDto createOfflineEvent(EventResquestDTO eventRequestDTO);

    PaginationResponseDto<EventResponseDto> getAllEvents(PaginationFullRequestDto paginationRequestDto);
    EventResponseDto getEventsByType(String type);
    EventResponseDto getEventsByDate(Date date);

    EventResponseDto updateEvent(String id, EventResquestDTO eventRequestDTO);
    CommonResponseDto deleteEvent(String id);
    PaginationResponseDto<Event> getAllEvent(PaginationFullRequestDto paginationFullRequestDto);
    List<Event> getAll();
}
