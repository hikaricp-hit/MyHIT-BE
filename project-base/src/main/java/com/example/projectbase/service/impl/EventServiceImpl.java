package com.example.projectbase.service.impl;

import com.example.projectbase.domain.entity.Event;
import com.example.projectbase.domain.dto.request.EventRequestDTO;
import com.example.projectbase.domain.dto.response.EventResponseDTO;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.exception.InvalidException;
import com.example.projectbase.repository.EventRepository;
import com.example.projectbase.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public static final String TYPE_CLASS = "Class";
    public static final String TYPE_ACTIVITY = "Activity";
    public static final String TYPE_OFFLINE = "Offline";

    @Override
    public List<EventResponseDTO> getAllEvents() {
        return eventRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventResponseDTO> getEventsByType(String type) {
        return eventRepository.findByType(type).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventResponseDTO> getEventsByDate(String date) {
        return eventRepository.findByDate(date).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public EventResponseDTO getEventById(String id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            return convertToDTO(eventOptional.get());
        } else {
            throw new NotFoundException("Event not found with id: " + id);
        }
    }

    @Override
    public EventResponseDTO createClassEvent(EventRequestDTO eventRequestDTO) {
        return createEventWithType(eventRequestDTO, TYPE_CLASS);
    }

    @Override
    public EventResponseDTO createActivityEvent(EventRequestDTO eventRequestDTO) {
        return createEventWithType(eventRequestDTO, TYPE_ACTIVITY);
    }

    @Override
    public EventResponseDTO createOfflineEvent(EventRequestDTO eventRequestDTO) {
        return createEventWithType(eventRequestDTO, TYPE_OFFLINE);
    }

    private EventResponseDTO createEventWithType(EventRequestDTO eventRequestDTO, String type) {
        if (eventRequestDTO.getName() == null || eventRequestDTO.getName().isEmpty()) {
            throw new InvalidException("Event name cannot be empty");
        }
        Event event = convertToEntity(eventRequestDTO);
        event.setType(type);
        Event savedEvent = eventRepository.save(event);
        return convertToDTO(savedEvent);
    }

    @Override
    public EventResponseDTO updateEvent(String id, EventRequestDTO eventRequestDTO) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            event.setName(eventRequestDTO.getName());
            event.setType(eventRequestDTO.getType());
            event.setLocation(eventRequestDTO.getLocation());
            event.setStartDate(eventRequestDTO.getStartDate());
            event.setEndDate(eventRequestDTO.getEndDate());
            event.setStartTime(eventRequestDTO.getStartTime());
            event.setEndTime(eventRequestDTO.getEndTime());
            if (!isValidEventType(event.getType())) {
                throw new InvalidException("Invalid event type");
            }
            Event updatedEvent = eventRepository.save(event);
            return convertToDTO(updatedEvent);
        } else {
            throw new NotFoundException("Event not found with id: " + id);
        }
    }

    @Override
    public void deleteEvent(String id) {
        if (!eventRepository.existsById(id)) {
            throw new NotFoundException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }

    private EventResponseDTO convertToDTO(Event event) {
        EventResponseDTO eventResponseDTO = new EventResponseDTO();
        eventResponseDTO.setId(event.getId());
        eventResponseDTO.setName(event.getName());
        eventResponseDTO.setType(event.getType());
        eventResponseDTO.setLocation(event.getLocation());
        eventResponseDTO.setStartDate(event.getStartDate());
        eventResponseDTO.setEndDate(event.getEndDate());
        eventResponseDTO.setStartTime(event.getStartTime());
        eventResponseDTO.setEndTime(event.getEndTime());
        return eventResponseDTO;
    }

    private Event convertToEntity(EventRequestDTO eventRequestDTO) {
        Event event = new Event();
        event.setName(eventRequestDTO.getName());
        event.setType(eventRequestDTO.getType());
        event.setLocation(eventRequestDTO.getLocation());
        event.setStartDate(eventRequestDTO.getStartDate());
        event.setEndDate(eventRequestDTO.getEndDate());
        event.setStartTime(eventRequestDTO.getStartTime());
        event.setEndTime(eventRequestDTO.getEndTime());
        return event;
    }

    private boolean isValidEventType(String type) {
        return TYPE_CLASS.equals(type) || TYPE_ACTIVITY.equals(type) || TYPE_OFFLINE.equals(type);
    }
}
