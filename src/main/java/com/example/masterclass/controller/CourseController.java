package com.example.masterclass.controller;

import com.example.masterclass.domain.courses.CourseCreateRequest;
import com.example.masterclass.domain.courses.CourseDetailResponse;
import com.example.masterclass.domain.courses.CourseListResponse;
import com.example.masterclass.domain.courses.CourseUpdateRequest;
import com.example.masterclass.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {
    "http://localhost:8081",
    "https://htw-masterclass-frontend.herokuapp.com"
})
public class CourseController {
    CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
       this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<CourseListResponse> getCourses() {
        return courseService.getAll();
    }

    @PostMapping("/courses")
    public CourseDetailResponse createCourse(@Valid @RequestBody CourseCreateRequest course) {
        return courseService.create(course);
    }

    @GetMapping("/courses/{id}")
    public CourseDetailResponse getCourse(@PathVariable String id) {
        Long courseId = Long.parseLong(id);
        return courseService.get(courseId);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable String id) {
        Long courseId = Long.parseLong(id);
        courseService.delete(courseId);
    }

    @PutMapping("/courses/{id}")
    public CourseDetailResponse updateCourse(
        @PathVariable String id,
        @Valid @RequestBody CourseUpdateRequest update
    ) {
        Long courseId = Long.parseLong(id);
        return courseService.update(courseId, update);
    }
}
