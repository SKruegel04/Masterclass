package com.example.masterclass.controller;

import com.example.masterclass.domain.Course;
import com.example.masterclass.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {
    CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
       this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return courseService.getAll();
    }

    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        return courseService.save(course);
    }

    @GetMapping("/courses/{id}")
    public Course getCourse(@PathVariable String id) {
        Long courseId = Long.parseLong(id);
        return courseService.get(courseId);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable String id) {
        Long courseId = Long.parseLong(id);
        courseService.delete(courseId);
    }

    @PutMapping("/courses/{id}")
    public Course updateCourse(@PathVariable String id, @RequestBody Course update) {
        Long courseId = Long.parseLong(id);
        return courseService.update(courseId, update);
    }
}
