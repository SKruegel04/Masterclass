package com.example.masterclass.service;

import com.example.masterclass.domain.courses.*;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseTransformerTest {

    @Test
    void createCourseListResponse() {
        CourseTransformer courseTransformer = new CourseTransformer();
        Course course = new Course(
            "Testkurs",
            "Testbeschreibung",
            "Testkategorie",
            Date.from(Instant.parse("2022-11-06T18:00:00.00Z")),
            60
        );
        CourseListResponse response = courseTransformer.createCourseListResponse(course);
        assertEquals("Testkurs", response.getTitle());
    }

    @Test
    void createCourseDetailResponse() {
        CourseTransformer courseTransformer = new CourseTransformer();
        Course course = new Course(
            "Testkurs",
            "Testbeschreibung",
            "Testkategorie",
            Date.from(Instant.parse("2022-11-06T18:00:00.00Z")),
            60
        );
        CourseDetailResponse response = courseTransformer.createCourseDetailResponse(course);
        assertEquals("Testkurs", response.getTitle());
    }

    @Test
    void createCourseFromCreateRequest(){
        CourseTransformer courseTransformer = new CourseTransformer();
        CourseCreateRequest courseCreateRequest = new CourseCreateRequest(
                "Testkurs",
                "Testbeschreibung",
                "Testkategorie",
                Date.from(Instant.parse("2022-11-06T18:00:00.00Z")),
                60
        );
        Course course = courseTransformer.createCourseFromCreateRequest(courseCreateRequest);
        assertEquals("Testkurs", course.getTitle());
    }

    @Test
    void updateCourseFromUpdateRequest() {
        CourseTransformer courseTransformer = new CourseTransformer();
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
            60
        );
        courseTransformer.updateCourseFromUpdateRequest(course, courseUpdateRequest);
        assertEquals("Testkurs 2", course.getTitle());
    }
}