package com.example.masterclass.service;

import com.example.masterclass.domain.courses.*;
import com.example.masterclass.domain.users.User;
import com.example.masterclass.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseTransformer {

    UserRepository userRepository;
    UserTransformer userTransformer;

    @Autowired
    public CourseTransformer(UserRepository userRepository, UserTransformer userTransformer) {
        this.userRepository = userRepository;
        this.userTransformer = userTransformer;
    }

    public CourseListResponse createCourseListResponse(Course course) {
        return new CourseListResponse(
            course.getId(),
            course.getTitle(),
            course.getDescription(),
            course.getCategory(),
            course.getDate(),
            course.getDuration(),
            userTransformer.createUserListResponse(course.getUser())
        );
    }

    public CourseDetailResponse createCourseDetailResponse(Course course) {
        return new CourseDetailResponse(
            course.getId(),
            course.getTitle(),
            course.getDescription(),
            course.getCategory(),
            course.getDate(),
            course.getDuration(),
            userTransformer.createUserListResponse(course.getUser())
        );
    }

    public Course createCourseFromCreateRequest(CourseCreateRequest request) {

        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = new Course(
            request.getTitle(),
            request.getDescription(),
            request.getCategory(),
            request.getDate(),
            request.getDuration()
        );
        course.setUser(user);
        return course;
    }

    public void updateCourseFromUpdateRequest(Course course, CourseUpdateRequest request) {

        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setCategory(request.getCategory());
        course.setDate(request.getDate());
        course.setDuration(request.getDuration());
        course.setUser(user);
    }
}
