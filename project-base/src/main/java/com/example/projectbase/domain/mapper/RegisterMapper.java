package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.CourseRequestDto;
import com.example.projectbase.domain.dto.request.RegisterRequestDto;
import com.example.projectbase.domain.dto.response.CourseDto;
import com.example.projectbase.domain.dto.response.RegisterDto;
import com.example.projectbase.domain.entity.Course;
import com.example.projectbase.domain.entity.Register;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    Register toEntity(RegisterRequestDto registerRequestDto);
    RegisterDto toDto(Register register);
}
