package com.example.masterclass.service;

import com.example.masterclass.domain.courses.Course;
import com.example.masterclass.domain.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    UserRepository userRepository;
    UserTransformer userTransformer;

    @Autowired
    public UserService(UserRepository userRepository, UserTransformer userTransformer) {
        this.userRepository = userRepository;
        this.userTransformer = userTransformer;
    }

    public UserDetailResponse create(UserCreateRequest request) {
        User user = userTransformer.createUserFromCreateRequest(request);
        User savedUser = userRepository.save(user);
        return userTransformer.createUserDetailResponse(savedUser);
    }

    public UserDetailResponse get(Long id) {
        return userRepository.findById(id)
            .map(userTransformer::createUserDetailResponse)
            .orElseThrow(RuntimeException::new);
    }

    public List<UserListResponse> getAll() {
        return userRepository.findAll()
            .stream()
            .map(userTransformer::createUserListResponse)
            .collect(Collectors.toList());
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserDetailResponse update(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
            .orElseThrow(RuntimeException::new);

        userTransformer.updateUserFromUpdateRequest(user, request);

        User savedUser = userRepository.save(user);
        return userTransformer.createUserDetailResponse(savedUser);
    }
}
