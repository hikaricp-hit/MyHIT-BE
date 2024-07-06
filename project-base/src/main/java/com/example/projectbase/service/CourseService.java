package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.CourseRequestDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.CourseDto;
import com.example.projectbase.domain.dto.response.NotificationDto;
import com.example.projectbase.domain.entity.Course;

import java.util.List;

public interface CourseService {
    CourseDto createCourse(CourseRequestDto courseRequestDto);
    List<CourseDto> readAllCourse(int page, int size);
    List<Course> readCourse(int page, int size);
    CourseDto findCourse(String courseName);
    CourseDto updateCourse(String courseId, CourseRequestDto courseRequestDto);
    void deleteCourse(String courseId);
}
