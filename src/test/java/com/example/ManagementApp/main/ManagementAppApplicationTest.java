package com.example.ManagementApp.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.ManagementApp.controller.UserController;
import com.example.ManagementApp.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ManagementAppApplicationTest {
    
    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;
    
    @Test
    public void contextLoads() {
        assertThat(userController).isNotNull();
        assertThat(userService).isNotNull();
    }
}
