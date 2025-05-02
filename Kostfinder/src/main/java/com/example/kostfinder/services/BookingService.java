package com.example.kostfinder.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kostfinder.Repository.BookingRepository;
import com.example.kostfinder.models.Booking;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Menambahkan booking baru
    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // Mengambil semua booking
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Mengambil booking berdasarkan ID
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    // Menghapus booking berdasarkan ID
    public void cancelBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Booking dengan ID " + id + " tidak ditemukan.");
        }
    }

    // Memperbarui data booking
    public Booking updateBooking(Long id, Booking updatedBooking) {
        return bookingRepository.findById(id)
                .map(existingBooking -> {
                    existingBooking.setBookingDate(updatedBooking.getBookingDate());
                    existingBooking.setKost(updatedBooking.getKost());
                    existingBooking.setUser(updatedBooking.getUser());
                    return bookingRepository.save(existingBooking);
                })
                .orElseThrow(() -> new IllegalArgumentException("Booking dengan ID " + id + " tidak ditemukan."));
    }
}
