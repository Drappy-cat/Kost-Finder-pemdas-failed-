package com.example.kostfinder.controllers;

import com.example.kostfinder.models.*;
import com.example.kostfinder.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class RegisterController {

    @Autowired
    private UserService userService;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.addUser(user);
        return ResponseEntity.ok(registeredUser);
    }
}