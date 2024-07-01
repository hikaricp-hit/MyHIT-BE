package com.example.projectbase.controller;

import com.example.projectbase.domain.dto.request.EventRequestDTO;
import com.example.projectbase.domain.dto.response.EventResponseDTO;
import com.example.projectbase.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class EventController {

    @Autowired
    private EventService eventService;

    @Tag(name = "event - controller")
    @Operation(summary = "API get all events")
    @GetMapping("/events")
    public List<EventResponseDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @Tag(name = "event - controller")
    @Operation(summary = "API get all Classes Events")
    @GetMapping("/events/class")
    public List<EventResponseDTO> getClassEvents() {
        return eventService.getEventsByType("Class");
    }

    @Tag(name = "event - controller")
    @Operation(summary = "API get all Activity Events")
    @GetMapping("/events/activity")
    public List<EventResponseDTO> getActivityEvents() {
        return eventService.getEventsByType("Activity");
    }

    @Tag(name = "event - controller")
    @Operation(summary = "API get all Offline Events")
    @GetMapping("/events/offline")
    public List<EventResponseDTO> getOfflineEvents() {
        return eventService.getEventsByType("Offline");
    }

    @Tag(name = "event - controller")
    @Operation(summary = "API get all events by date")
    @GetMapping("/events/date/{date}")
    public List<EventResponseDTO> getEventsByDate(@PathVariable String date) {
        return eventService.getEventsByDate(date);
    }

    @Tag(name = "event - controller")
    @Operation(summary = "API get event by ID")
    @GetMapping("/events/{id}")
    public EventResponseDTO getEventById(@PathVariable String id) {
        return eventService.getEventById(id);
    }

    @Tag(name = "event - controller - admin")
    @Operation(summary = "API create Class event")
    @PostMapping("/events/class")
    public EventResponseDTO createClassEvent(@RequestBody EventRequestDTO eventRequestDTO) {
        return eventService.createClassEvent(eventRequestDTO);
    }

    @Tag(name = "event - controller - admin")
    @Operation(summary = "API create Activity event")
    @PostMapping("/events/activity")
    public EventResponseDTO createActivityEvent(@RequestBody EventRequestDTO eventRequestDTO) {
        return eventService.createActivityEvent(eventRequestDTO);
    }

    @Tag(name = "event - controller - admin")
    @Operation(summary = "API create Offline event")
    @PostMapping("/events/offline")
    public EventResponseDTO createOfflineEvent(@RequestBody EventRequestDTO eventRequestDTO) {
        return eventService.createOfflineEvent(eventRequestDTO);
    }

    @Tag(name = "event - controller - admin")
    @Operation(summary = "API update event")
    @PutMapping("/events/{id}")
    public EventResponseDTO updateEvent(@PathVariable String id, @RequestBody EventRequestDTO eventRequestDTO) {
        return eventService.updateEvent(id, eventRequestDTO);
    }

    @Tag(name = "event - controller - admin")
    @Operation(summary = "API delete event")
    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
    }
}
