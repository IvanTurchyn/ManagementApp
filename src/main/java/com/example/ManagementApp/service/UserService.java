package com.example.ManagementApp.service;
import com.example.ManagementApp.model.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.ManagementApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        if (user.getId() == null) {
            user.setAdded(new Date());
        }
        user.setModified(new Date());
        return userRepository.save(user);
    }

    public Optional<User> findById(String id){
        return userRepository.findById(id);
    }
    public User updateUser(String id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setModified(new Date());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
