package com.example.kostfinder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.kostfinder.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {}

