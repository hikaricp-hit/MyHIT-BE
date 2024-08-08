package com.example.projectbase.controller.admincontroller;

import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.request.CourseRequestDto;
import com.example.projectbase.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public class CourseAdminController {
    private final CourseService courseService;

    public CourseAdminController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Tag(name = "course-controller-admin")
    @Operation(summary = "API create course")
    @PostMapping("/admin/course")
    public ResponseEntity<?> createCourse(@RequestBody CourseRequestDto courseRequestDto) {
        return VsResponseUtil.success(courseService.createCourse(courseRequestDto));
    }

    @Tag(name = "course-controller-admin")
    @Operation(summary = "API get all courses for admin")
    @GetMapping("/admin/course/admin")
    public ResponseEntity<?> readCourse(@Valid @ParameterObject PaginationFullRequestDto paginationRequestDto) {
        return VsResponseUtil.success(courseService.readCourse(paginationRequestDto));
    }

    @Tag(name = "course-controller-admin")
    @Operation(summary = "API update course")
    @PutMapping("/admin/course")
    public ResponseEntity<?> updateCourse(@RequestParam String courseId, @RequestBody CourseRequestDto courseRequestDto) {
        return VsResponseUtil.success(courseService.updateCourse(courseId, courseRequestDto));
    }

    @Tag(name = "course-controller-admin")
    @Operation(summary = "API delete course")
    @DeleteMapping("/admin/course")
    public ResponseEntity<?> deleteCourse(@RequestParam String courseId) {
        return VsResponseUtil.success(courseService.deleteCourse(courseId));
    }
}
