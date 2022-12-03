package com.example.masterclass.service;

import com.example.masterclass.domain.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void create() {
        UserService userService = new UserService(
            userRepository,
            new UserTransformer()
        );

    }
}
