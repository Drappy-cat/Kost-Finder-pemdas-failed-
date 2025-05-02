package com.kostfinder.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KostDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Latitude is required")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    private Double longitude;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    private BigDecimal depositAmount;

    @NotNull(message = "Total rooms is required")
    @Positive(message = "Total rooms must be positive")
    private Integer totalRooms;

    private Integer availableRooms;

    private Set<Long> facilityIds;
    private List<String> rules;

    @NotBlank(message = "Owner phone is required")
    private String ownerPhone;

    private Boolean wifi = false;
    private Boolean ac = false;
    private Boolean bathroom = false;
    private Boolean furniture = false;

    @NotBlank(message = "Gender preference is required")
    private String gender; // MALE, FEMALE, MIXED

    private Long ownerId;
    private List<String> imageUrls;
    
    // Additional kost details
    private Integer roomSize;
    private String paymentTerm; // MONTHLY, YEARLY
    private Boolean isAvailable = true;
    private String area; // Location area/district
    private Double distanceToUniversity;
    private List<String> nearbyFacilities;
}
