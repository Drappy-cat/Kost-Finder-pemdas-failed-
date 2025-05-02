package com.example.kostfinder.controller;

import com.example.kostfinder.models.Reservasi;
import com.example.kostfinder.dto.ReservasiDTO;
import com.example.kostfinder.services.ReservasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservasi")
public class ReservasiController {

    @Autowired
    private ReservasiService reservasiService;

    @PostMapping
    public ResponseEntity<Reservasi> createReservasi(@RequestBody ReservasiDTO reservasiDTO) {
        Reservasi newReservasi = reservasiService.createReservasi(reservasiDTO);
        return ResponseEntity.ok(newReservasi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservasi> getReservasiById(@PathVariable Long id) {
        Reservasi reservasi = reservasiService.getReservasiById(id);
        return ResponseEntity.ok(reservasi);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservasi>> getUserReservations(@PathVariable Long userId) {
        List<Reservasi> reservations = reservasiService.getUserReservations(userId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/kost/{kostId}")
    public ResponseEntity<List<Reservasi>> getKostReservations(@PathVariable Long kostId) {
        List<Reservasi> reservations = reservasiService.getKostReservations(kostId);
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservasi> updateReservasi(@PathVariable Long id, @RequestBody ReservasiDTO reservasiDTO) {
        Reservasi updatedReservasi = reservasiService.updateReservasi(id, reservasiDTO);
        return ResponseEntity.ok(updatedReservasi);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelReservasi(@PathVariable Long id) {
        reservasiService.cancelReservasi(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Reservasi> confirmReservasi(@PathVariable Long id) {
        Reservasi confirmedReservasi = reservasiService.confirmReservasi(id);
        return ResponseEntity.ok(confirmedReservasi);
    }
}
