package com.example.kostfinder.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Kost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String price;

    private String facilities;

    @OneToMany(mappedBy = "kost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    // Constructor kosong
    public Kost() {}

    // Constructor dengan parameter
    public Kost(String name, String location, String price, String facilities) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.facilities = facilities;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPrice() {
        return price;
    }

    public String getFacilities() {
        return facilities;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
