package com.example.masterclass.service;

import com.example.masterclass.domain.users.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTransformerTest {

    @Test
    void createUserListResponse() {
        UserTransformer userTransformer = new UserTransformer();
        User user = new User(
            "Testname",
            "Testbeschreibung"
        );
        UserListResponse response = userTransformer.createUserListResponse(user);
        assertEquals("Testname", response.getName());
    }

    @Test
    void createUserDetailResponse() {
        UserTransformer userTransformer = new UserTransformer();
        User user = new User(
            "Testname",
            "Testbeschreibung"
        );
        UserDetailResponse response = userTransformer.createUserDetailResponse(user);
        assertEquals("Testname", response.getName());
    }

    @Test
    void createUserFromCreateRequest(){
        UserTransformer userTransformer = new UserTransformer();
        UserCreateRequest userCreateRequest = new UserCreateRequest(
            "Testname",
            "Testbeschreibung"
        );
        User user = userTransformer.createUserFromCreateRequest(userCreateRequest);
        assertEquals("Testname", user.getName());
    }

    @Test
    void updateUserFromUpdateRequest() {
        UserTransformer userTransformer = new UserTransformer();
        User user = new User(
            "Testname",
            "Testbeschreibung"
        );
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest(
            "Testname 2",
            "Testbeschreibung"
        );
        userTransformer.updateUserFromUpdateRequest(user, userUpdateRequest);
        assertEquals("Testname 2", user.getName());
    }
}