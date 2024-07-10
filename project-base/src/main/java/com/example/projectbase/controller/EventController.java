package com.example.projectbase.controller;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.EventRequestDTO;
import com.example.projectbase.domain.dto.response.EventResponseDto;
import com.example.projectbase.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/schedule")
public class EventController {

    @Autowired
    private EventService eventService;

    @Tag(name = "event - controller")
    @Operation(summary = "API get all events")
    @GetMapping("/events")
    public PaginationResponseDto<EventResponseDto> getAllEvents(@RequestBody PaginationFullRequestDto paginationFullRequestDto) {
        return eventService.getAllEvents(paginationFullRequestDto);
    }

    @Tag(name = "event - controller")
    @Operation(summary = "API get all Classes Events")
    @GetMapping("/events/class")
    public PaginationResponseDto<EventResponseDto> getClassEvents(@RequestBody PaginationFullRequestDto paginationFullRequestDto) {
        return eventService.getEventsByType("Class", paginationFullRequestDto);
    }

    @Tag(name = "event - controller")
    @Operation(summary = "API get all Activity Events")
    @GetMapping("/events/activity")
    public PaginationResponseDto<EventResponseDto> getActivityEvents(@RequestBody PaginationFullRequestDto paginationFullRequestDto) {
        return eventService.getEventsByType("Activity", paginationFullRequestDto);
    }

    @Tag(name = "event - controller")
    @Operation(summary = "API get all Offline Events")
    @GetMapping("/events/offline")
    public PaginationResponseDto<EventResponseDto> getOfflineEvents(@RequestBody PaginationFullRequestDto paginationFullRequestDto) {
        return eventService.getEventsByType("Offline", paginationFullRequestDto);
    }

    @Tag(name = "event - controller")
    @Operation(summary = "API get all events by date")
    @GetMapping("/events/date")
    public PaginationResponseDto<EventResponseDto> getEventsByDate(@RequestParam Date date, @RequestBody PaginationFullRequestDto paginationFullRequestDto) {
        return eventService.getEventsByDate(date, paginationFullRequestDto);
    }

    @Tag(name = "event - controller")
    @Operation(summary = "API get event by ID")
    @GetMapping("/events/{id}")
    public EventResponseDto getEventById(@PathVariable String id) {
        return eventService.getEventById(id);
    }

    @Tag(name = "event - controller - admin")
    @Operation(summary = "API create Class event")
    @PostMapping("/events/class")
    public EventResponseDto createClassEvent(@RequestBody EventRequestDTO eventRequestDTO) {
        return eventService.createClassEvent(eventRequestDTO);
    }

    @Tag(name = "event - controller - admin")
    @Operation(summary = "API create Activity event")
    @PostMapping("/events/activity")
    public EventResponseDto createActivityEvent(@RequestBody EventRequestDTO eventRequestDTO) {
        return eventService.createActivityEvent(eventRequestDTO);
    }

    @Tag(name = "event - controller - admin")
    @Operation(summary = "API create Offline event")
    @PostMapping("/events/offline")
    public EventResponseDto createOfflineEvent(@RequestBody EventRequestDTO eventRequestDTO) {
        return eventService.createOfflineEvent(eventRequestDTO);
    }

    @Tag(name = "event - controller - admin")
    @Operation(summary = "API update event")
    @PutMapping("/events/{id}")
    public EventResponseDto updateEvent(@PathVariable String id, @RequestBody EventRequestDTO eventRequestDTO) {
        return eventService.updateEvent(id, eventRequestDTO);
    }

    @Tag(name = "event - controller - admin")
    @Operation(summary = "API delete event")
    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
    }
}
