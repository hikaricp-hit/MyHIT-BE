package com.example.projectbase.service.impl;

import com.example.projectbase.domain.entity.Event;
import com.example.projectbase.domain.dto.request.EventRequestDTO;
import com.example.projectbase.domain.dto.response.EventResponseDTO;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.exception.InvalidException;
import com.example.projectbase.repository.EventRepository;
import com.example.projectbase.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

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
    public EventResponseDTO createEvent(EventRequestDTO eventRequestDTO) {
        if (eventRequestDTO.getName() == null || eventRequestDTO.getName().isEmpty()) {
            throw new InvalidException("Event name cannot be empty");
        }
        Event event = convertToEntity(eventRequestDTO);
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
}
