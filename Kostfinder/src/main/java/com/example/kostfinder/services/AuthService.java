package com.example.kostfinder.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kostfinder.Repository.UserRepository;
import com.example.kostfinder.models.User;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public String register(User user) {
        userRepository.save(user);
        return "User registered successfully!";
    }

    public String login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return "Login successful!";
        }
        return "Invalid credentials!";
    }
}


