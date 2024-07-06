package com.example.projectbase.service.impl;


import com.example.projectbase.domain.dto.request.CourseRequestDto;
import com.example.projectbase.domain.dto.response.CourseDto;
import com.example.projectbase.domain.entity.Course;
import com.example.projectbase.domain.mapper.CourseMapper;
import com.example.projectbase.repository.CourseRepository;
import com.example.projectbase.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseDto createCourse(CourseRequestDto courseRequestDto) {
        Course course = courseMapper.toEntity(courseRequestDto);
        return courseMapper.toDto(courseRepository.save(course));
    }

    @Override
    public List<CourseDto> readAllCourse(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursesPage = courseRepository.findAll(pageable);
        return  coursesPage.stream().map(courseMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Course> readCourse(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursesPage = courseRepository.findAll(pageable);
        return  coursesPage.toList();
    }

    @Override
    public CourseDto findCourse(String courseName) {
        Course course= courseRepository.findCourseByName(courseName);
        return courseMapper.toDto(course);
    }

    @Override
    public CourseDto updateCourse(String courseId, CourseRequestDto courseRequestDto) {
        Optional<Course> course = courseRepository.findById(courseId);
        course.get().setName(courseRequestDto.getName());
        course.get().setDetail(courseRequestDto.getDetail());
        course.get().setLeader(courseRequestDto.getLeader());
        course.get().setStartDate(courseRequestDto.getStartDate());
        course.get().setPicture(courseRequestDto.getPicture());
        course.get().setStatus(courseRequestDto.getStatus());
        return courseMapper.toDto(courseRepository.save(course.get()));
    }

    @Override
    public void deleteCourse(String courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        courseRepository.deleteById(courseId);
    }
}
