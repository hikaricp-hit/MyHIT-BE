package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.EventResquestDTO;
import com.example.projectbase.domain.dto.response.EventResponseDto;
import com.example.projectbase.domain.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event toEntity(EventResquestDTO eventRequestDTO);
    EventResponseDto toDto(Event event);
}
