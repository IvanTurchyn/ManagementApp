package com.example.ManagementApp.service;

import com.example.ManagementApp.model.User;
import com.example.ManagementApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveUserTest() {
        User user = new User("Jan", "Kowalski","test@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertEquals(user, savedUser);
    }

    @Test
    public void updateUserTest() {
        User user = new User("Jana", "Kowalska","test@example1.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.updateUser(user);
        assertNotNull(updatedUser);
        assertEquals(user, updatedUser);
    }

    @Test
    public void findByEmailTest() {
        User user = new User("Janka", "Kowalskaja","test@example2.com");
        when(userRepository.findByEmail(any(String.class))).thenReturn(user);

        User foundUser = userService.findByEmail("test@example2.com");
        assertNotNull(foundUser);
        assertEquals(user, foundUser);
    }

    @Test
    public void deleteUserTest() {
        User user = new User("Anna", "Kowal","test@example4.com");
        userService.deleteUser(user.getId());
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = new ArrayList<>();
        users.add(new User("Karp", "Kowalskij","test@example5.com"));
        users.add(new User("Akas", "Kowa","test@example6.com"));
        when(userRepository.findAll()).thenReturn(users);

        List<User> allUsers = userService.getAllUsers();
        assertEquals(users,allUsers);

    }
}