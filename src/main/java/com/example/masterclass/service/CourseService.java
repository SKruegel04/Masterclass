package com.example.masterclass.service;

import com.example.masterclass.domain.Course;
import com.example.masterclass.domain.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }
    public Course get(Long id) {
        return courseRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    public Course update(Long id, Course update) {
        Course course = get(id);

        course.setTitle(update.getTitle());
        course.setDescription(update.getDescription());
        course.setCategory(update.getCategory());
        course.setDate(update.getDate());
        course.setDuration(update.getDuration());

        return courseRepository.save(course);
    }
}
