package com.example.masterclass.service;

import com.example.masterclass.domain.courses.*;
import com.example.masterclass.domain.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CourseTransformerTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void createCourseListResponse() {
        CourseTransformer courseTransformer = new CourseTransformer(userRepository, new UserTransformer());
        Course course = new Course(
            "Testkurs",
            "Testbeschreibung",
            "Testkategorie",
            Date.from(Instant.parse("2022-11-06T18:00:00.00Z")),
            60
        );
        course.setUser(userRepository.findById(1L).orElseThrow());
        CourseListResponse response = courseTransformer.createCourseListResponse(course);
        assertEquals("Testkurs", response.getTitle());
    }

    @Test
    void createCourseDetailResponse() {
        CourseTransformer courseTransformer = new CourseTransformer(userRepository, new UserTransformer());
        Course course = new Course(
            "Testkurs",
            "Testbeschreibung",
            "Testkategorie",
            Date.from(Instant.parse("2022-11-06T18:00:00.00Z")),
            60
        );
        course.setUser(userRepository.findById(1L).orElseThrow());
        CourseDetailResponse response = courseTransformer.createCourseDetailResponse(course);
        assertEquals("Testkurs", response.getTitle());
    }

    @Test
    void createCourseFromCreateRequest(){
        CourseTransformer courseTransformer = new CourseTransformer(userRepository, new UserTransformer());
        CourseCreateRequest courseCreateRequest = new CourseCreateRequest(
                "Testkurs",
                "Testbeschreibung",
                "Testkategorie",
                Date.from(Instant.parse("2022-11-06T18:00:00.00Z")),
                60,
                1
        );
        Course course = courseTransformer.createCourseFromCreateRequest(courseCreateRequest);
        assertEquals("Testkurs", course.getTitle());
    }

    @Test
    void updateCourseFromUpdateRequest() {
        CourseTransformer courseTransformer = new CourseTransformer(userRepository, new UserTransformer());
        Course course = new Course(
            "Testkurs",
            "Testbeschreibung",
            "Testkategorie",
            Date.from(Instant.parse("2022-11-06T18:00:00.00Z")),
            60
        );
        CourseUpdateRequest courseUpdateRequest = new CourseUpdateRequest(
            "Testkurs 2",
            "Testbeschreibung",
            "Testkategorie",
            Date.from(Instant.parse("2022-11-06T18:00:00.00Z")),
            60,
            1L
        );
        courseTransformer.updateCourseFromUpdateRequest(course, courseUpdateRequest);
        assertEquals("Testkurs 2", course.getTitle());
    }
}