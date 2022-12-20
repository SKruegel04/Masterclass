package com.example.masterclass.service;

import com.example.masterclass.domain.courses.*;
import com.example.masterclass.domain.users.UserRepository;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CourseServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Test
    void create() {
        CourseService courseService = new CourseService(
            courseRepository,
            new CourseTransformer(userRepository, new UserTransformer())
        );

        CourseCreateRequest courseCreateRequest = new CourseCreateRequest(
            "Ballett Anfänger",
            "Ballett für Anfänger und Tänzer die ihre Technik verbessern wollen.",
            "Ballett",
            Date.from(Instant.parse("2022-12-02T15:00:00Z")),
            90,
            1
        );
        CourseDetailResponse response = courseService.create(courseCreateRequest);
        assertEquals("Ballett Anfänger", response.getTitle());
        assertEquals("Ballett für Anfänger und Tänzer die ihre Technik verbessern wollen.", response.getDescription());
        assertEquals("Ballett", response.getCategory());
        assertEquals("2022-12-02T15:00:00Z", response.getDate().toInstant().toString());
        assertEquals(90, response.getDuration());
    }

    @Test
    void get() {
        CourseService courseService = new CourseService(
            courseRepository,
            new CourseTransformer(userRepository, new UserTransformer())
        );

        courseService.create(new CourseCreateRequest(
            "Ballett Anfänger",
            "Ballett für Anfänger und Tänzer die ihre Technik verbessern wollen.",
            "Ballett",
            Date.from(Instant.parse("2022-12-10T18:00:00.00Z")),
            90,
            1
        ));
        CourseDetailResponse createResponse = courseService.create(new CourseCreateRequest(
            "Ballett Anfänger",
            "Ballett für Anfänger und Tänzer die ihre Technik verbessern wollen.",
            "Ballett",
            Date.from(Instant.parse("2022-12-10T18:00:00.00Z")),
            90,
            1
        ));
        courseService.create(new CourseCreateRequest(
            "Contemporary Fortgeschritten",
            "Contemporary für Fortgeschrittene",
            "Contemporary",
            Date.from(Instant.parse("2022-12-13T18:00:00.00Z")),
            90,
            1
        ));

        CourseDetailResponse getResponse = courseService.get(createResponse.getId());

        assertEquals(createResponse.getId(), getResponse.getId());
        assertEquals(createResponse.getTitle(), getResponse.getTitle());
        assertEquals(createResponse.getDescription(), getResponse.getDescription());
        assertEquals(createResponse.getCategory(), getResponse.getCategory());
        assertEquals(createResponse.getDate(), getResponse.getDate());
        assertEquals(createResponse.getDuration(), getResponse.getDuration());
    }

    @Test
    void getAll() {
        CourseService courseService = new CourseService(
            courseRepository,
            new CourseTransformer(userRepository, new UserTransformer())
        );

        List<CourseListResponse> courses = courseService.getAll();

        CourseListResponse course1 = courses.get(0);
        assertFalse(Strings.isNullOrEmpty(course1.getTitle()));
        CourseListResponse course3 = courses.get(2);
        assertFalse(Strings.isNullOrEmpty(course3.getDescription()));
    }

    @Test
    void delete() {
        CourseService courseService = new CourseService(
                courseRepository,
                new CourseTransformer(userRepository, new UserTransformer())
        );

        List<CourseListResponse> courses = courseService.getAll();
        CourseListResponse firstCourse = courses.get(0);

        courseService.delete(firstCourse.getId());

        assertThrows(RuntimeException.class, () -> courseService.get(firstCourse.getId()));
    }

    @Test
    void update() {
        CourseService courseService = new CourseService(
                courseRepository,
                new CourseTransformer(userRepository, new UserTransformer())
        );
        CourseDetailResponse createResponse = courseService.create(
                new CourseCreateRequest("Contemporary Mittelstufe", "Contemporary mit mittlerem Level",
                        "Contemporary", Date.from(Instant.parse("2022-12-10T18:00:00.00Z")), 90, 1L)
        );
        courseService.update(
                createResponse.getId(),
                new CourseUpdateRequest("Contemporary Mittelstufe", "Contemporary mit mittlerem Level",
                        "Contemporary", Date.from(Instant.parse("2022-12-10T18:00:00.00Z")), 60, 1L)
        );
        CourseDetailResponse getResponse = courseService.get(createResponse.getId());

        assertEquals("Contemporary Mittelstufe", getResponse.getTitle());
        assertEquals("Contemporary mit mittlerem Level", getResponse.getDescription());
    }
}
