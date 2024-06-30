package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.EventRequestDTO;
import com.example.projectbase.domain.dto.response.EventResponseDTO;

import java.util.List;

public interface EventService {
    List<EventResponseDTO> getAllEvents();
    List<EventResponseDTO> getEventsByType(String type);
    List<EventResponseDTO> getEventsByDate(String date);
    EventResponseDTO getEventById(String id);
    EventResponseDTO createClassEvent(EventRequestDTO eventRequestDTO);
    EventResponseDTO createActivityEvent(EventRequestDTO eventRequestDTO);
    EventResponseDTO createOfflineEvent(EventRequestDTO eventRequestDTO);
    EventResponseDTO updateEvent(String id, EventRequestDTO eventRequestDTO);
    void deleteEvent(String id);
}
