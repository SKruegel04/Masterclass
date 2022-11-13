package com.example.masterclass.service;

import com.example.masterclass.domain.courses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    CourseRepository courseRepository;

    CourseTransformer courseTransformer;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseTransformer courseTransformer) {
        this.courseRepository = courseRepository;
        this.courseTransformer = courseTransformer;
    }

    public CourseDetailResponse create(CourseCreateRequest request) {
        Course course = courseTransformer.createCourseFromCreateRequest(request);
        Course savedCourse = courseRepository.save(course);
        return courseTransformer.createCourseDetailResponse(savedCourse);
    }

    public CourseDetailResponse get(Long id) {
        return courseRepository.findById(id)
            .map(courseTransformer::createCourseDetailResponse)
            .orElseThrow(RuntimeException::new);
    }

    public List<CourseListResponse> getAll() {
        return courseRepository.findAll()
            .stream()
            .map(courseTransformer::createCourseListResponse)
            .collect(Collectors.toList());
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    public CourseDetailResponse update(Long id, CourseUpdateRequest request) {
        Course course = courseRepository.findById(id)
            .orElseThrow(RuntimeException::new);

        courseTransformer.updateCourseFromUpdateRequest(course, request);

        Course savedCourse = courseRepository.save(course);
        return courseTransformer.createCourseDetailResponse(savedCourse);
    }
}
