package com.example.ManagementApp.controller;

import com.example.ManagementApp.model.User;
import com.example.ManagementApp.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public void addUser(User user) {
        userService.saveUser(user);
    }

    @GetMapping
    public List<User> findAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> findById (@PathVariable String id){
        return userService.findById(id);
    }

    @GetMapping("/email/{email}")
    public User findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @GetMapping("/{id}/timestamps")
    public Map<String, Object> getTimestamps(@PathVariable("id") String id) {
        Optional<User> optionalUser = userService.findById(id);
        Map<String, Object> timestamps = new HashMap<>();
        optionalUser.ifPresent(user -> {
            timestamps.put("firstName", user.getFirstName());
            timestamps.put("lastName", user.getLastName());
            timestamps.put("email", user.getEmail());
            timestamps.put("added", user.getAdded());
            timestamps.put("modified", user.getModified());
        });
        return timestamps;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}