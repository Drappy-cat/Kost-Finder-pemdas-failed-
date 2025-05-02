package com.example.kostfinder.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "kost_id", nullable = false)
    private Kost kost;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    // Constructor kosong (dibutuhkan oleh JPA)
    public Booking() {}

    // Constructor dengan parameter (opsional jika diperlukan)
    public Booking(User user, Kost kost, LocalDate bookingDate) {
        this.user = user;
        this.kost = kost;
        this.bookingDate = bookingDate;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Kost getKost() {
        return kost;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setKost(Kost kost) {
        this.kost = kost;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
}