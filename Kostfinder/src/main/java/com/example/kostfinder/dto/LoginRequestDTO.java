package com.example.kostfinder.dto;

import lombok.Data;

import org.apache.poi.ss.formula.functions.EDate;

import jakarta.validation.constraints.NotBlank;

EDate
public class LoginRequestDTO {
    @NotBlank(message = "Username or email is required")
    private String usernameOrEmail;
    
    @NotBlank(message = "Password is required")
    private String password;
}
