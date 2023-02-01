package com.example.ManagementApp.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.ManagementApp.model.User;
import com.example.ManagementApp.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void addUser_ShouldReturnHttpStatusCreated() {
        User user = new User("John", "Doe", "john.doe@example.com");
        when(userService.saveUser(user)).thenReturn(user);

        ResponseEntity<String> response = userController.addUser(user);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getBody(), is("Dane użytkownika zapisane pomyślnie"));
    }

    @Test
    public void findAllUsers_ShouldReturnListOfUsers() {
        User user1 = new User("John", "Doe", "john.doe@example.com");
        User user2 = new User("Jane", "Doe", "jane.doe@example.com");
        List<User> users = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(users);

        List<User> response = userController.findAllUsers();

        assertThat(response, is(users));
    }

    @Test
    public void findByEmail_ShouldReturnUser() {
        User user = new User("John", "Doe", "john.doe@example.com");
        when(userService.findByEmail("john.doe@example.com")).thenReturn(user);

        User response = userController.findByEmail("john.doe@example.com");

        assertThat(response, is(user));
    }

    @Test
    public void updateUser_ShouldReturnUpdatedUser() {
        User user = new User("John", "Doe", "john.doe@example.com");
        when(userService.updateUser(user)).thenReturn(user);

        User response = userController.updateUser("1", user);

        assertThat(response, is(user));
    }

    @Test
    public void deleteUser_ShouldCallDeleteUserMethodFromUserService() {
        userController.deleteUser("1");
    }
}