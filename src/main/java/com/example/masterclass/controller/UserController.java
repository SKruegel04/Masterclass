package com.example.masterclass.controller;

import com.example.masterclass.domain.users.*;
import com.example.masterclass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Was ist die Aufgabe des <EntityName>Controller?
 * - Definiert REST-Endpunkte, Dokumentation/Definition für REST-API
 * - Leitet tatsächliche Aufgaben an <EntityName>Service
 */
@RestController
@CrossOrigin(origins = {
    "http://localhost:8081",
    "https://htw-masterclass-frontend.herokuapp.com"
})
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserListResponse> getUsers() {
        return userService.getAll();
    }

    @PostMapping("/users")
    public UserDetailResponse createUser(@RequestBody UserCreateRequest request) {
        return userService.create(request);
    }

    @GetMapping("/users/{id}")
    public UserDetailResponse getUser(@PathVariable String id) {
        Long userId = Long.parseLong(id);
        return userService.get(userId);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        Long courseId = Long.parseLong(id);
        userService.delete(courseId);
    }

    @PutMapping("/users/{id}")
    public UserDetailResponse updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        Long userId = Long.parseLong(id);
        return userService.update(userId, request);
    }
}
