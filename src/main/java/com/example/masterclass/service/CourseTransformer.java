package com.example.masterclass.service;

import com.example.masterclass.domain.courses.*;
import org.springframework.stereotype.Service;

@Service
public class CourseTransformer {
    public CourseListResponse createCourseListResponse(Course course) {
        return new CourseListResponse(
            course.getId(),
            course.getTitle(),
            course.getDescription(),
            course.getCategory(),
            course.getDate(),
            course.getDuration()
        );
    }

    public CourseDetailResponse createCourseDetailResponse(Course course) {
        return new CourseDetailResponse(
            course.getId(),
            course.getTitle(),
            course.getDescription(),
            course.getCategory(),
            course.getDate(),
            course.getDuration()
        );
    }

    public Course createCourseFromCreateRequest(CourseCreateRequest request) {
        return new Course(
            request.getTitle(),
            request.getDescription(),
            request.getCategory(),
            request.getDate(),
            request.getDuration()
        );
    }

    public void updateCourseFromUpdateRequest(Course course, CourseUpdateRequest request) {
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setCategory(request.getCategory());
        course.setDate(request.getDate());
        course.setDuration(request.getDuration());
    }
}
