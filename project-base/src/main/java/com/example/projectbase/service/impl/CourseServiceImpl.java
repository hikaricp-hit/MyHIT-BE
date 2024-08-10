package com.example.projectbase.service.impl;


import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.MessageConstrant;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.pagination.PagingMeta;
import com.example.projectbase.domain.dto.request.CourseRequestDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.CourseDto;
import com.example.projectbase.domain.entity.Course;
import com.example.projectbase.domain.entity.Notification;
import com.example.projectbase.domain.mapper.CourseMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.CourseRepository;
import com.example.projectbase.service.CourseService;
import com.example.projectbase.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public List<Course> getAll() {
        List<Course> courses = courseRepository.findAll();
        return courses;
    }

    @Override
    public PaginationResponseDto<Course> readAllCourse(PaginationFullRequestDto paginationRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationRequestDto);

        Page<Course> coursesPage = courseRepository.findAll(pageable);

        PagingMeta pagingMeta = new PagingMeta(
                coursesPage.getTotalElements(),
                coursesPage.getTotalPages(),
                coursesPage.getNumber(),
                coursesPage.getSize(),
                paginationRequestDto.getSortBy(),
                paginationRequestDto.getIsAscending().toString()
        );

        List<Course> courseList= coursesPage.toList();

        return new PaginationResponseDto<>(pagingMeta, courseList);
    }


    @Override
    public PaginationResponseDto<Course> readCourse(PaginationFullRequestDto paginationRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationRequestDto);
        Page<Course> coursesPage = courseRepository.findAll(pageable);
        PagingMeta pagingMeta = new PagingMeta(
                coursesPage.getTotalElements(),
                coursesPage.getTotalPages(),
                coursesPage.getNumber(),
                coursesPage.getSize(),
                paginationRequestDto.getSortBy(),
                paginationRequestDto.getIsAscending().toString()
        );

        List<Course> courseList= coursesPage.toList();
        return new PaginationResponseDto<>(pagingMeta, courseList);
    }

    @Override
    public CourseDto findCourse(String courseName) {
        Course course= courseRepository.findCourseByName(courseName);
        return courseMapper.toDto(course);
    }

    @Override
    public CourseDto updateCourse(String courseId, CourseRequestDto courseRequestDto) {
        Optional<Course> course = Optional.ofNullable(courseRepository.findById(courseId).orElseThrow(() ->
                new NotFoundException(ErrorMessage.Course.ERR_NOT_FOUND_ID, new String[]{courseId})));
        course.get().setName(courseRequestDto.getName());
        course.get().setDetail(courseRequestDto.getDetail());
        course.get().setLeader(courseRequestDto.getLeader());
        course.get().setStartDate(courseRequestDto.getStartDate());
        course.get().setPicture(courseRequestDto.getPicture());
        course.get().setStatus(courseRequestDto.getStatus());
        return courseMapper.toDto(courseRepository.save(course.get()));
    }

    @Override
    public CommonResponseDto deleteCourse(String courseId) {
        Optional<Course> course = Optional.ofNullable(courseRepository.findById(courseId).orElseThrow(() ->
                new NotFoundException(ErrorMessage.Course.ERR_NOT_FOUND_ID, new String[]{courseId})));
        courseRepository.deleteById(courseId);
        return  new CommonResponseDto(true, MessageConstrant.SUCCESS);
    }
}
