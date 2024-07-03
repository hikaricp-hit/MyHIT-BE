package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.entity.Event;
import com.example.projectbase.domain.dto.request.EventRequestDTO;
import com.example.projectbase.domain.dto.response.EventResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(target = "id", ignore = true) // Ignore mapping the 'id' field
    Event toEntity(EventRequestDTO dto);

    EventResponseDto toDTO(Event entity);

    @Mapping(target = "id", ignore = true) // Ignore mapping the 'id' field
    void updateEntityFromDTO(EventRequestDTO dto, @MappingTarget Event entity);
}
