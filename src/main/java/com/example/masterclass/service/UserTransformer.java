package com.example.masterclass.service;

import com.example.masterclass.domain.users.*;
import org.springframework.stereotype.Service;

@Service
public class UserTransformer {
    public UserListResponse createUserListResponse(User user) {
        return new UserListResponse(user.getId(), user.getName(), user.getDescription());
    }

    public UserDetailResponse createUserDetailResponse(User user) {
        return new UserDetailResponse(user.getId(), user.getName(), user.getDescription());
    }

    public User createUserFromCreateRequest(UserCreateRequest request) {
        return new User(
            request.getName(),
            request.getDescription()
        );
    }

    public void updateUserFromUpdateRequest(User user, UserUpdateRequest request) {
        user.setName(request.getName());
        user.setDescription(request.getDescription());
    }
}
