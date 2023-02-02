package com.example.ManagementApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.ManagementApp.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByEmail(String email);

    public User save(User user);

    public void deleteById(String id);

    public Optional<User> findById(String id);
}
