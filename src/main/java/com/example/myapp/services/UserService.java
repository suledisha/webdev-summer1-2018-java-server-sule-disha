package com.example.myapp.services;

import com.example.myapp.models.User;
import com.example.myapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserService {
    @Autowired
    UserRepository repository;

    @GetMapping("/api/user")
    public List<User> findAllUsers() {
        return (List<User>) repository.findAll();
    }
}
