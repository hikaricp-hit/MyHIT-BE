package com.example.projectbase.controller;

import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.request.EventResquestDTO;
import com.example.projectbase.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class EventController {

    private final EventService eventService;

    @Tag(name = "event - controller")
    @Operation(summary = "API get all events")
    @GetMapping("/events")
    public ResponseEntity<?> getAllEvents(@Valid @ParameterObject PaginationFullRequestDto paginationRequestDto) {
        return VsResponseUtil.success(eventService.getAllEvents(paginationRequestDto));
    }

    @Tag(name = "event - controller")
    @Operation(summary = "API get events by type")
    @GetMapping("/events/type")
    public ResponseEntity<?> getEventsByType(@RequestParam String type) {
        return VsResponseUtil.success(eventService.getEventsByType(type));
    }

    @Tag(name = "event - controller")
    @Operation(summary = "API get events by date")
    @GetMapping("/events/date")
    public ResponseEntity<?> getEventsByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return VsResponseUtil.success(eventService.getEventsByDate(date));
    }

    @Tag(name = "event - controller - admin")
    @Operation(summary = "API create Class event")
    @PostMapping("/events/class")
    public ResponseEntity<?> createClassEvent(@RequestBody EventResquestDTO eventRequestDTO) {
        return VsResponseUtil.success(eventService.createClassEvent(eventRequestDTO));
    }

    @Tag(name = "event - controller - admin")
    @Operation(summary = "API create Activity event")
    @PostMapping("/events/activity")
    public ResponseEntity<?> createActivityEvent(@RequestBody EventResquestDTO eventRequestDTO) {
        return VsResponseUtil.success(eventService.createActivityEvent(eventRequestDTO));
    }

    @Tag(name = "event - controller - admin")
    @Operation(summary = "API create Offline event")
    @PostMapping("/events/offline")
    public ResponseEntity<?> createOfflineEvent(@RequestBody EventResquestDTO eventRequestDTO) {
        return VsResponseUtil.success(eventService.createOfflineEvent(eventRequestDTO));
    }

    @Tag(name = "event - controller - admin")
    @Operation(summary = "API update event")
    @PutMapping("/events/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable String id, @RequestBody EventResquestDTO eventRequestDTO) {
        return VsResponseUtil.success(eventService.updateEvent(id, eventRequestDTO));
    }

    @Tag(name = "event - controller - admin")
    @Operation(summary = "API delete event")
    @DeleteMapping("/events/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable String id) {
        return VsResponseUtil.success(eventService.deleteEvent(id));
    }
}
