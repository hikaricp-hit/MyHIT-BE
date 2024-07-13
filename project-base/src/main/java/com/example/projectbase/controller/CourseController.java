package com.example.projectbase.controller;

import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.request.CourseRequestDto;
import com.example.projectbase.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @Tag(name = "course-controller-admin")
    @Operation(summary = "API create course")
    @PostMapping("/admin/course")
    public ResponseEntity<?> createCourse(@RequestBody CourseRequestDto courseRequestDto) {
        return VsResponseUtil.success(courseService.createCourse(courseRequestDto));
    }

    @Tag(name = "course-controller")
    @Operation(summary = "API get all courses")
    @GetMapping("/user/course")
    public ResponseEntity<?> readAllCourse(@Valid @ParameterObject PaginationFullRequestDto paginationRequestDto) {
        return VsResponseUtil.success(courseService.readAllCourse(paginationRequestDto));
    }

    @Tag(name = "course-controller-admin")
    @Operation(summary = "API get all courses for admin")
    @GetMapping("/admin/course/admin")
    public ResponseEntity<?> readCourse(@Valid @ParameterObject PaginationFullRequestDto paginationRequestDto) {
        return VsResponseUtil.success(courseService.readCourse(paginationRequestDto));
    }

    @Tag(name = "course-controller")
    @Operation(summary = "API find course by name")
    @GetMapping("/user/course/find")
    public ResponseEntity<?> readAllCourse(@RequestParam String courseName) {
        return VsResponseUtil.success(courseService.findCourse(courseName));
    }

    @Tag(name = "course-controller-admin")
    @Operation(summary = "API update course")
    @PutMapping("/admin/course")
    public ResponseEntity<?> updateCourse(@RequestParam String courseId,@RequestBody CourseRequestDto courseRequestDto) {
        return VsResponseUtil.success(courseService.updateCourse(courseId, courseRequestDto));
    }

    @Tag(name = "course-controller-admin")
    @Operation(summary = "API delete course")
    @DeleteMapping("/admin/course")
    public ResponseEntity<?> deleteCourse(@RequestParam String courseId) {
        return VsResponseUtil.success(courseService.deleteCourse(courseId));
    }
}
