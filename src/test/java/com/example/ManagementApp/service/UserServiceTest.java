package com.example.ManagementApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.ManagementApp.model.User;
import com.example.ManagementApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void saveUserTest() {
        User user = new User("firstName", "lastName", "email");
        when(userRepository.save(user)).thenReturn(user);
        User actualUser = userService.saveUser(user);
        verify(userRepository, times(1)).save(user);
        assertEquals(user, actualUser);
    }

    @Test
    public void findByIdTest() {
        User expectedUser = new User("firstName", "lastName", "email");
        when(userRepository.findById(anyString())).thenReturn(Optional.of(expectedUser));
        Optional<User> actualUser = userService.findById("id");
        verify(userRepository, times(1)).findById(anyString());
        assertEquals(expectedUser, actualUser.get());
    }

    @Test
    public void updateUserTest() {
        User expectedUser = new User("firstName", "lastName", "email");
        User existingUser = new User("existingFirstName", "existingLastName", "existingEmail");
        when(userRepository.findById(anyString())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(expectedUser);
        User actualUser = userService.updateUser("id", expectedUser);
        verify(userRepository, times(1)).findById(anyString());
        verify(userRepository, times(1)).save(existingUser);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void findByEmailTest() {
        User expectedUser = new User("firstName", "lastName", "email");
        when(userRepository.findByEmail(anyString())).thenReturn(expectedUser);
        User actualUser = userService.findByEmail("email");
        verify(userRepository, times(1)).findByEmail(anyString());
        assertEquals(expectedUser, actualUser);
    }
}