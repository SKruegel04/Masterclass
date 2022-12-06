package com.example.masterclass.service;

import com.example.masterclass.domain.users.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void create() {
        UserService userService = new UserService(
            userRepository,
            new UserTransformer()
        );

        UserCreateRequest userCreateRequest = new UserCreateRequest(
            "Margot Fonteyn",
            "Ballettlehrerin"
        );
        UserDetailResponse response = userService.create(userCreateRequest);
        assertEquals("Margot Fonteyn", response.getName());
        assertEquals("Ballettlehrerin", response.getDescription());
    }

    @Test
    void get() {
        UserService userService = new UserService(
            userRepository,
            new UserTransformer()
        );

        userService.create(new UserCreateRequest("Marcie Haydee", "Ballettlehrerin"));
        UserDetailResponse createResponse =  userService.create(
            new UserCreateRequest("Martha Grayham", "Contemporarylehrerin")
        );
        userService.create(new UserCreateRequest("Amanda Nunez", "MMA-Trainerin"));

        UserDetailResponse getResponse = userService.get(createResponse.getId());

        assertEquals(createResponse.getId(), getResponse.getId());
        assertEquals(createResponse.getName(), getResponse.getName());
        assertEquals(createResponse.getDescription(), getResponse.getDescription());
    }

    @Test
    void getAll() {
        UserService userService = new UserService(
            userRepository,
            new UserTransformer()
        );
        userService.create(new UserCreateRequest("Marcie Haydee", "Ballettlehrerin"));
        userService.create(new UserCreateRequest("Martha Grayham", "Contemporarylehrerin"));
        userService.create(new UserCreateRequest("Amanda Nunez", "MMA-Trainerin"));

        List<UserListResponse> users = userService.getAll();

        assertEquals(3, users.size());
        UserListResponse user1 = users.get(0);
        assertEquals("Marcie Haydee", user1.getName());
        UserListResponse user3 = users.get(2);
        assertEquals("MMA-Trainerin", user3.getDescription());
    }

    @Test
    void delete() {
        UserService userService = new UserService(
            userRepository,
            new UserTransformer()
        );
        userService.create(new UserCreateRequest("Marcie Haydee", "Ballettlehrerin"));
        UserDetailResponse response = userService.create(
            new UserCreateRequest("Martha Grayham", "Contemporarylehrerin")
        );
        userService.create(new UserCreateRequest("Amanda Nunez", "MMA-Trainerin"));

        userService.delete(response.getId());

        assertThrows(RuntimeException.class, () -> userService.get(response.getId()));

        List<UserListResponse> users = userService.getAll();
        assertEquals(2, users.size());
    }

    @Test
    void update() {
        UserService userService = new UserService(
                userRepository,
                new UserTransformer()
        );
        UserDetailResponse createResponse = userService.create(
            new UserCreateRequest("Martha Grayham", "Contemporarylehrerin")
        );
        userService.update(
            createResponse.getId(),
            new UserUpdateRequest("Martha Nureyev", "Contemporarylehrerin")
        );
        UserDetailResponse getResponse = userService.get(createResponse.getId());

        assertEquals("Martha Nureyev", getResponse.getName());
        assertEquals("Contemporarylehrerin", getResponse.getDescription());
    }
}