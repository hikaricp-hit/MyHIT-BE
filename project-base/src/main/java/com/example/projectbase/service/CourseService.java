package com.example.projectbase.service;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.CourseRequestDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.CourseDto;
import com.example.projectbase.domain.dto.response.NotificationDto;
import com.example.projectbase.domain.entity.Course;

import java.util.List;

public interface CourseService {
    CourseDto createCourse(CourseRequestDto courseRequestDto);
    PaginationResponseDto<CourseDto> readAllCourse(PaginationFullRequestDto paginationRequestDto);
    List<Course> readCourse(PaginationRequestDto paginationRequestDto);
    CourseDto findCourse(String courseName);
    CourseDto updateCourse(String courseId, CourseRequestDto courseRequestDto);
    CommonResponseDto deleteCourse(String courseId);
}
