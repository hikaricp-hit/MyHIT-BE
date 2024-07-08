package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Course;
import com.example.projectbase.domain.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    Page<Course> findAll(org.springframework.data.domain.Pageable pageable);
    Course findCourseByName(String courseName);
    Course findCourseById(String courseId);
}
