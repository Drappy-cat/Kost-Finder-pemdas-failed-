package com.example.kostfinder.dto;

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