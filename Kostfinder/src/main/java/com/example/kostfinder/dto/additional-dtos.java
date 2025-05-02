// FasilitasDTO.java
package com.kostfinder.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FasilitasDTO {
    private Long id;
    
    @NotBlank(message = "Facility name is required")
    private String name;
    
    private String description;
    private String icon;
    private Boolean isCommon = false; // whether it's a common facility or room facility
}

// UniversitasDTO.java
package com.kostfinder.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversitasDTO {
    private Long id;
    
    @NotBlank(message = "University name is required")
    private String name;
    
    @NotBlank(message = "Address is required")
    private String address;
    
    @NotNull(message = "Latitude is required")
    private Double latitude;
    
    @NotNull(message = "Longitude is required")
    private Double longitude;
    
    private String website;
    private String phoneNumber;
}

// FotoKostDTO.java
package com.kostfinder.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FotoKostDTO {
    private Long id;
    private Long kostId;
    private String url;
    private String caption;
    private Boolean isMain = false;
    private String fileName;
    private String fileType;
    private Long fileSize;
}

// LoginRequestDTO.java
package com.kostfinder.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestDTO {
    @NotBlank(message = "Username or email is required")
    private String usernameOrEmail;
    
    @NotBlank(message = "Password is required")
    private String password;
}

// LoginResponseDTO.java
package com.kostfinder.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private UserDTO user;
}
