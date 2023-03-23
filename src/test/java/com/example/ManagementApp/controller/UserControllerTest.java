package com.example.ManagementApp.controller;

import com.example.ManagementApp.model.User;
import com.example.ManagementApp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addUser() {
        User user = new User("firstName", "lastName", "email");
        userController.addUser(user);
        verify(userService, times(1)).saveUser(user);
    }

    @Test
    void findAllUsers() {
        List<User> expectedUsers = Arrays.asList(
                new User("firstName1", "lastName1", "email1"),
                new User("firstName2", "lastName2", "email2")
        );
        when(userService.getAllUsers()).thenReturn(expectedUsers);
        List<User> actualUsers = userController.findAllUsers();
        verify(userService, times(1)).getAllUsers();
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void findById() {
        Optional<User> expectedUser = Optional.of(new User("firstName", "lastName", "email"));
        when(userService.findById(anyString())).thenReturn(expectedUser);
        Optional<User> actualUser = userController.findById("id");
        verify(userService, times(1)).findById(anyString());
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void findByEmail() {
        User expectedUser = new User("firstName", "lastName", "email");
        when(userService.findByEmail(anyString())).thenReturn(expectedUser);
        User actualUser = userController.findByEmail("email");
        verify(userService, times(1)).findByEmail(anyString());
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void updateUser() {
        User expectedUser = new User("firstName", "lastName", "email");
        when(userService.updateUser(anyString(), any(User.class))).thenReturn(expectedUser);
        User actualUser = userController.updateUser("id", expectedUser);
        verify(userService, times(1)).updateUser(anyString(), any(User.class));
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void deleteUser() {
        String userId = "test-id";
        doNothing().when(userService).deleteUser(userId);
        userController.deleteUser(userId);
        verify(userService, times(1)).deleteUser(userId);
    }
}