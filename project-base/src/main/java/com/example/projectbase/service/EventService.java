package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.EventRequestDTO;
import com.example.projectbase.domain.dto.response.EventResponseDTO;
import java.util.Date;
import java.util.List;

public interface EventService {

    List<EventResponseDTO> getAllEvents(int page, int size);

    List<EventResponseDTO> getEventsByType(String type, int page, int size);

    List<EventResponseDTO> getEventsByDate(Date date, int page, int size);

    EventResponseDTO getEventById(String id);

    EventResponseDTO createClassEvent(EventRequestDTO eventRequestDTO);

    EventResponseDTO createActivityEvent(EventRequestDTO eventRequestDTO);

    EventResponseDTO createOfflineEvent(EventRequestDTO eventRequestDTO);

    EventResponseDTO updateEvent(String id, EventRequestDTO eventRequestDTO);

    boolean deleteEvent(String id);
}
