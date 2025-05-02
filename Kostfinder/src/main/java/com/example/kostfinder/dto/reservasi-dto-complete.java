package com.kostfinder.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservasiDTO {
    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Kost ID is required")
    private Long kostId;

    @NotNull(message = "Check-in date is required")
    @Future(message = "Check-in date must be in the future")
    private LocalDate checkInDate;

    @NotNull(message = "Check-out date is required")
    @Future(message = "Check-out date must be in the future")
    private LocalDate checkOutDate;

    @NotNull(message = "Duration type is required")
    private String durationType; // MONTHLY, YEARLY

    private String paymentMethod;
    private String paymentStatus; // PENDING, PAID, CANCELLED
    private BigDecimal totalAmount;
    private String specialRequests;
    private String status; // PENDING, CONFIRMED, CANCELLED, COMPLETED

    private String rejectionReason;
    private LocalDate paymentDueDate;
    private Boolean isExtended = false;
    private String bookingCode;
}
