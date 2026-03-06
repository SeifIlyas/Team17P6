package com.hirespace.hirespace_api.controller;

import org.springframework.web.bind.annotation.*;

import com.hirespace.hirespace_api.model.User;
import com.hirespace.hirespace_api.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginData) {
        Optional<User> user = userRepository.findByEmail(loginData.getEmail());

        if (user.isPresent() && user.get().getPassword().equals(loginData.getPassword())) {
            return user.get();
        }

        return null;
    }
}