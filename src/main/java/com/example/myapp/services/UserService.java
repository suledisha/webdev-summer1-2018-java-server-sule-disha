package com.example.myapp.services;

import com.example.myapp.models.User;
import com.example.myapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
public class UserService {
    @Autowired
    UserRepository repository;

    @GetMapping("/api/user")
    public List<User> findAllUsers() {
        return (List<User>) repository.findAll();
    }

    @PostMapping("/api/user")
    public User createUser(@RequestBody User user) {
        return repository.save(user);
    }

    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int id) {
        repository.deleteById(id);
    }

    @PostMapping("/api/login")
    public User login(@RequestBody User user, HttpServletResponse response) {
        Optional<User> data = repository.findUserByCredentials(user.getUsername(), user.getPassword());
        if(data.isPresent()) {
            return data.get();
        }
        response.setStatus(HttpServletResponse.SC_CONFLICT);
        return null;
    }

    @PutMapping("/api/user/{userId}")
    public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser, HttpServletResponse response) {
        Optional<User> data = repository.findById(userId);
        if(data.isPresent()) {
            User user = data.get();
            user.setUsername(newUser.getUsername());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setRole(newUser.getRole());
            repository.save(user);
            return user;
        }
        response.setStatus(HttpServletResponse.SC_CONFLICT);
        return null;
    }

    @GetMapping("/api/user/{userId}")
    public User findUserById(@PathVariable("userId") int userId) {
        Optional<User> data = repository.findById(userId);
        if(data.isPresent()) {
            return data.get();
        }
        return null;
    }

    @GetMapping("api/register/{username}")
    public User findUserByUsername(@PathVariable("username") String username, HttpServletResponse response) {
        Optional<User> data = repository.findUserByUsername(username);
        if(data.isPresent()) {
            return data.get();
        }
        response.setStatus(HttpServletResponse.SC_CONFLICT);
        return null;
    }
}
