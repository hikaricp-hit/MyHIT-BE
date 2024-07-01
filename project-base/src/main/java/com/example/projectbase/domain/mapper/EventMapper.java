package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.entity.Event;
import com.example.projectbase.domain.dto.request.EventRequestDTO;
import com.example.projectbase.domain.dto.response.EventResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    Event toEntity(EventRequestDTO dto);

    EventResponseDTO toDTO(Event entity);
}
