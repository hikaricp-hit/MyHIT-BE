package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.MessageConstrant;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.pagination.PagingMeta;
import com.example.projectbase.domain.dto.request.EventResquestDTO;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.EventResponseDto;
import com.example.projectbase.domain.entity.Event;
import com.example.projectbase.domain.mapper.EventMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.EventRepository;
import com.example.projectbase.service.EventService;
import com.example.projectbase.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public EventResponseDto createClassEvent(EventResquestDTO eventRequestDTO) {
        Event event = eventMapper.toEntity(eventRequestDTO);
        event.setType("Class");
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toDto(savedEvent);
    }

    @Override
    public EventResponseDto createActivityEvent(EventResquestDTO eventRequestDTO) {
        Event event = eventMapper.toEntity(eventRequestDTO);
        event.setType("Activity");
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toDto(savedEvent);
    }

    @Override
    public EventResponseDto createOfflineEvent(EventResquestDTO eventRequestDTO) {
        Event event = eventMapper.toEntity(eventRequestDTO);
        event.setType("Offline");
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toDto(savedEvent);
    }

    @Override
    public PaginationResponseDto<EventResponseDto> getAllEvents(PaginationFullRequestDto paginationRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationRequestDto);

        Page<Event> eventsPage = eventRepository.findAll(pageable);

        PagingMeta pagingMeta = new PagingMeta(
                eventsPage.getTotalElements(),
                eventsPage.getTotalPages(),
                eventsPage.getNumber(),
                eventsPage.getSize(),
                paginationRequestDto.getSortBy(),
                paginationRequestDto.getIsAscending().toString()
        );

        List<EventResponseDto> eventsDtoList = eventsPage.stream()
                .map(event -> eventMapper.toDto(event))
                .collect(Collectors.toList());

        return new PaginationResponseDto<>(pagingMeta, eventsDtoList);
    }

    public EventResponseDto getEventsByType(String type) {
        Event event= eventRepository.findByType(type);
        return eventMapper.toDto(event);
    }

    public EventResponseDto getEventsByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Event event= eventRepository.findByStartDate(date, calendar.getTime());
        return eventMapper.toDto(event);
    }

    @Override
    public EventResponseDto updateEvent(String eventId, EventResquestDTO eventRequestDTO) {
        Optional<Event> event = Optional.ofNullable(eventRepository.findById(eventId).orElseThrow(() ->
                new NotFoundException(ErrorMessage.Event.EVENT_NOT_FOUND, new String[]{eventId})));
        event.get().setName(eventRequestDTO.getName());
        event.get().setType(eventRequestDTO.getType());
        event.get().setLocation(eventRequestDTO.getLocation());
        event.get().setStartDate(eventRequestDTO.getStartDate());
        event.get().setEndDate(eventRequestDTO.getEndDate());
        return eventMapper.toDto(eventRepository.save(event.get()));
    }

    @Override
    public CommonResponseDto deleteEvent(String eventId) {
        Optional<Event> event = Optional.ofNullable(eventRepository.findById(eventId).orElseThrow(() ->
                new NotFoundException(ErrorMessage.Course.ERR_NOT_FOUND_ID, new String[]{eventId})));
        eventRepository.deleteById(eventId);
        return  new CommonResponseDto(true, MessageConstrant.SUCCESS);
    }
}
