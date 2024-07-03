package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.EventRequestDTO;
import com.example.projectbase.domain.dto.response.EventResponseDto;

import java.util.Date;
import java.util.List;

public interface EventService {
    List<EventResponseDto> getAllEvents(int page, int size);
    List<EventResponseDto> getEventsByType(String type, int page, int size);
    List<EventResponseDto> getEventsByDate(Date date, int page, int size);
    EventResponseDto getEventById(String id);
    EventResponseDto createClassEvent(EventRequestDTO eventRequestDTO);
    EventResponseDto createActivityEvent(EventRequestDTO eventRequestDTO);
    EventResponseDto createOfflineEvent(EventRequestDTO eventRequestDTO);
    EventResponseDto updateEvent(String id, EventRequestDTO eventRequestDTO);
    boolean deleteEvent(String id);
}
