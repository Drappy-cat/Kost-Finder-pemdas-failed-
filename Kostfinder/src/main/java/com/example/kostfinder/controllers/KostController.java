package com.example.kostfinder.controllers;

import com.example.kostfinder.models.*;
import com.example.kostfinder.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kosts")
public class KostController {

    @Autowired
    private KostService kostService;

    // mendapatkan semua kost
    @GetMapping
    public ResponseEntity<List<Kost>> getAllKosts() {
        List<Kost> kosts = kostService.getAllKosts();
        return ResponseEntity.ok(kosts);
    }

    // Get kost by ID
    @GetMapping("/{id}")
    public ResponseEntity<Kost> getKostById(@PathVariable Long id) {
        Optional<Kost> kost = kostService.getKostById(id);
        return kost.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // Add a new kost
    @PostMapping
    public ResponseEntity<Kost> addKost(@RequestBody Kost kost) {
        Kost savedKost = kostService.addKost(kost);
        return ResponseEntity.ok(savedKost);
    }

    // Update existing kost
    @PutMapping("/{id}")
    public ResponseEntity<Kost> updateKost(@PathVariable Long id, @RequestBody Kost updatedKost) {
        Kost kost = kostService.updateKost(id, updatedKost);
        if (kost != null) {
            return ResponseEntity.ok(kost);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete kost
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKost(@PathVariable Long id) {
        kostService.deleteKost(id);
        return ResponseEntity.noContent().build();
    }
}