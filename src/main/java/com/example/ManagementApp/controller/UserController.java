package com.example.ManagementApp.controller;

import com.example.ManagementApp.model.User;
import com.example.ManagementApp.service.UserService;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> addUser(User user) {
        User savedUser = userService.saveUser(user);
        if (savedUser != null) {
            return new ResponseEntity<>("Dane użytkownika zapisane pomyślnie", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Wystąpił błąd podczas zapisywania danych użytkownika", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<User> findAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/email/{email}")
    public User findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}