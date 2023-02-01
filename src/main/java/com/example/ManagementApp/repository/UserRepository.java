package com.example.ManagementApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.ManagementApp.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByEmail(String email);

    public User save(User user);

    public void deleteById(String id);
}
