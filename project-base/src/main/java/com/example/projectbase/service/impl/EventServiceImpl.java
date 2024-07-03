package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.EventConstant;
import com.example.projectbase.domain.entity.Event;
import com.example.projectbase.domain.dto.request.EventRequestDTO;
import com.example.projectbase.domain.dto.response.EventResponseDto;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.exception.InvalidException;
import com.example.projectbase.domain.mapper.EventMapper;
import com.example.projectbase.repository.EventRepository;
import com.example.projectbase.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper = EventMapper.INSTANCE;

    @Override
    public List<EventResponseDto> getAllEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventsPage = eventRepository.findAll(pageable);
        return eventsPage.stream().map(eventMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventResponseDto> getEventsByType(String type, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventsPage = eventRepository.findByType(type, pageable);
        return eventsPage.stream().map(eventMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<EventResponseDto> getEventsByDate(Date date, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventsPage = eventRepository.findByDate(date, pageable);
        return eventsPage.stream().map(eventMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public EventResponseDto getEventById(String id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            return eventMapper.toDTO(eventOptional.get());
        } else {
            throw new NotFoundException(ErrorMessage.Event.EVENT_NOT_FOUND + id);
        }
    }

    @Override
    public EventResponseDto createClassEvent(EventRequestDTO eventRequestDTO) {
        return createEventWithType(eventRequestDTO, EventConstant.TYPE_CLASS);
    }

    @Override
    public EventResponseDto createActivityEvent(EventRequestDTO eventRequestDTO) {
        return createEventWithType(eventRequestDTO, EventConstant.TYPE_ACTIVITY);
    }

    @Override
    public EventResponseDto createOfflineEvent(EventRequestDTO eventRequestDTO) {
        return createEventWithType(eventRequestDTO, EventConstant.TYPE_OFFLINE);
    }

    private EventResponseDto createEventWithType(EventRequestDTO eventRequestDTO, String type) {
        if (eventRequestDTO.getName() == null || eventRequestDTO.getName().isEmpty()) {
            throw new InvalidException(ErrorMessage.Event.EMPTY_EVENT_NAME);
        }
        Event event = eventMapper.toEntity(eventRequestDTO);
        event.setType(type);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toDTO(savedEvent);
    }

    @Override
    public EventResponseDto updateEvent(String id, EventRequestDTO eventRequestDTO) {
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
                throw new InvalidException(ErrorMessage.Event.INVALID_EVENT_TYPE);
            }
            Event updatedEvent = eventRepository.save(event);
            return eventMapper.toDTO(updatedEvent);
        } else {
            throw new NotFoundException(ErrorMessage.Event.EVENT_NOT_FOUND + id);
        }
    }

    @Override
    public boolean deleteEvent(String id) {
        if (!eventRepository.existsById(id)) {
            throw new NotFoundException(ErrorMessage.Event.EVENT_NOT_FOUND + id);
        }
        eventRepository.deleteById(id);
        return !eventRepository.existsById(id);
    }

    private boolean isValidEventType(String type) {
        return EventConstant.TYPE_CLASS.equals(type) || EventConstant.TYPE_ACTIVITY.equals(type) || EventConstant.TYPE_OFFLINE.equals(type);
    }
}
