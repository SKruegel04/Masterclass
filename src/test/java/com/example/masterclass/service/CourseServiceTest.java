package com.example.masterclass.service;

import com.example.masterclass.domain.courses.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CourseServiceTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    void create() {
        CourseService courseService = new CourseService(
                courseRepository,
                new CourseTransformer()
        );

        CourseCreateRequest courseCreateRequest = new CourseCreateRequest(
                "Ballett Anfänger",
                "Ballett für Anfänger und Tänzer die ihre Technik verbessern wollen.",
                "Ballett",
                Date.from(Instant.parse("2022-12-02T15:00:00Z")),
                90
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
            new CourseTransformer()
        );

        courseService.create(new CourseCreateRequest(
            "Ballett Anfänger",
            "Ballett für Anfänger und Tänzer die ihre Technik verbessern wollen.",
            "Ballett",
            Date.from(Instant.parse("2022-12-10T18:00:00.00Z")),
            90
        ));
        CourseDetailResponse createResponse = courseService.create(new CourseCreateRequest(
            "Ballett Anfänger",
            "Ballett für Anfänger und Tänzer die ihre Technik verbessern wollen.",
            "Ballett",
            Date.from(Instant.parse("2022-12-10T18:00:00.00Z")),
            90
        ));
        courseService.create(new CourseCreateRequest(
            "Contemporary Fortgeschritten",
            "Contemporary für Fortgeschrittene",
            "Contemporary",
            Date.from(Instant.parse("2022-12-13T18:00:00.00Z")),
            90
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
                new CourseTransformer()
        );
        courseService.create(new CourseCreateRequest("Ballett Anfänger", "Ballett für Anfänger", "Ballett",
                Date.from(Instant.parse("2022-12-10T18:00:00.00Z")), 90));
        courseService.create(new CourseCreateRequest("Contemporary Mittelstufe", "Contemporary für Mittelstufe",
                "Contemporary", Date.from(Instant.parse("2022-12-07T18:00:00.00Z")), 90));
        courseService.create(new CourseCreateRequest("MMA Anfänger", "MMA für Anfänger", "MMA",
                Date.from(Instant.parse("2022-12-19T18:00:00.00Z")), 60));


        List<CourseListResponse> courses = courseService.getAll();

        assertEquals(3, courses.size());
        CourseListResponse course1 = courses.get(0);
        assertEquals("Ballett Anfänger", course1.getTitle());
        CourseListResponse course3 = courses.get(2);
        assertEquals("MMA für Anfänger", course3.getDescription());
    }

    @Test
    void delete() {
        CourseService courseService = new CourseService(
                courseRepository,
                new CourseTransformer()
        );
        courseService.create(new CourseCreateRequest("Ballett Fortgeschritten", "Ballett für Fortgeschrittene",
                "Ballett", Date.from(Instant.parse("2022-12-07T18:00:00.00Z")), 90));
        CourseDetailResponse response = courseService.create(
                new CourseCreateRequest("Contemporary Mittelstufe", "Contemporary mit mittlerem Level",
                        "Contemporary", Date.from(Instant.parse("2022-12-10T18:00:00.00Z")), 90));

        courseService.create(new CourseCreateRequest("MMA Basic", "MMA für Anfänger", "MMA",
                Date.from(Instant.parse("2022-12-17T19:00:00.00Z")), 60));

        courseService.delete(response.getId());

        assertThrows(RuntimeException.class, () -> courseService.get(response.getId()));

        List<CourseListResponse> courses = courseService.getAll();
        assertEquals(2, courses.size());
    }

    @Test
    void update() {
        CourseService courseService = new CourseService(
                courseRepository,
                new CourseTransformer()
        );
        CourseDetailResponse createResponse = courseService.create(
                new CourseCreateRequest("Contemporary Mittelstufe", "Contemporary mit mittlerem Level",
                        "Contemporary", Date.from(Instant.parse("2022-12-10T18:00:00.00Z")), 90)
        );
        courseService.update(
                createResponse.getId(),
                new CourseUpdateRequest("Contemporary Mittelstufe", "Contemporary mit mittlerem Level",
                        "Contemporary", Date.from(Instant.parse("2022-12-10T18:00:00.00Z")), 60)
        );
        CourseDetailResponse getResponse = courseService.get(createResponse.getId());

        assertEquals("Contemporary Mittelstufe", getResponse.getTitle());
        assertEquals("Contemporary mit mittlerem Level", getResponse.getDescription());
    }
}
