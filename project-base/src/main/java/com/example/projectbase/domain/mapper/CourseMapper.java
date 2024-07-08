package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.CourseRequestDto;
import com.example.projectbase.domain.dto.request.NotificationCreateDto;
import com.example.projectbase.domain.dto.response.CourseDto;
import com.example.projectbase.domain.dto.response.NotificationDto;
import com.example.projectbase.domain.entity.Course;
import com.example.projectbase.domain.entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseRequestDto courseRequestDto);
    CourseDto toDto(Course course);
}
