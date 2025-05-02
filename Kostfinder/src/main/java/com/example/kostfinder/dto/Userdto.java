package com.example.kostfinder.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Userdto {
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Student ID is required")
    private String studentId; // NIM mahasiswa

    private Long universityId;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private String role; // ROLE_USER, ROLE_ADMIN

    private Boolean isVerified;
    
    // DTO for user response (without sensitive data)
    public UserDTO withoutSensitiveData() {
        UserDTO dto = new UserDTO();
        dto.setId(this.id);
        dto.setUsername(this.username);
        dto.setEmail(this.email);
        dto.setFullName(this.fullName);
        dto.setStudentId(this.studentId);
        dto.setUniversityId(this.universityId);
        dto.setPhoneNumber(this.phoneNumber);
        dto.setRole(this.role);
        dto.setIsVerified(this.isVerified);
        return dto;
    }
}