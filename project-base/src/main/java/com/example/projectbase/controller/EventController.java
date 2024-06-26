package com.example.projectbase.controller;

import com.example.projectbase.domain.dto.request.EventRequestDTO;
import com.example.projectbase.domain.dto.response.EventResponseDTO;
import com.example.projectbase.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public List<EventResponseDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/events/class")
    public List<EventResponseDTO> getClassEvents() {
        return eventService.getEventsByType("Class");
    }

    @GetMapping("/events/activity")
    public List<EventResponseDTO> getActivityEvents() {
        return eventService.getEventsByType("Activity");
    }

    @GetMapping("/events/offline")
    public List<EventResponseDTO> getOfflineEvents() {
        return eventService.getEventsByType("Offline");
    }

    @GetMapping("/events/date/{date}")
    public List<EventResponseDTO> getEventsByDate(@PathVariable String date) {
        return eventService.getEventsByDate(date);
    }

    @GetMapping("/events/{id}")
    public EventResponseDTO getEventById(@PathVariable String id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/events")
    public EventResponseDTO createEvent(@RequestBody EventRequestDTO eventRequestDTO) {
        return eventService.createEvent(eventRequestDTO);
    }

    @PutMapping("/events/{id}")
    public EventResponseDTO updateEvent(@PathVariable String id, @RequestBody EventRequestDTO eventRequestDTO) {
        return eventService.updateEvent(id, eventRequestDTO);
    }

    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
    }
}
