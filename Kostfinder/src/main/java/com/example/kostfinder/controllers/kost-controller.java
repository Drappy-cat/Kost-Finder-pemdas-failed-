package com.example.kostfinder.controller;

import com.example.kostfinder.models.Kost;
import com.example.kostfinder.dto.KostDTO;
import com.example.kostfinder.services.KostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/kosts")
public class KostController {

    @Autowired
    private KostService kostService;

    @PostMapping
    public ResponseEntity<Kost> createKost(@RequestBody KostDTO kostDTO) {
        Kost newKost = kostService.createKost(kostDTO);
        return ResponseEntity.ok(newKost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kost> getKostById(@PathVariable Long id) {
        Kost kost = kostService.getKostById(id);
        return ResponseEntity.ok(kost);
    }

    @GetMapping
    public ResponseEntity<Page<Kost>> getAllKosts(Pageable pageable, 
                                                @RequestParam(required = false) Double maxPrice,
                                                @RequestParam(required = false) String location,
                                                @RequestParam(required = false) Boolean available) {
        Page<Kost> kosts = kostService.searchKosts(pageable, maxPrice, location, available);
        return ResponseEntity.ok(kosts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kost> updateKost(@PathVariable Long id, @RequestBody KostDTO kostDTO) {
        Kost updatedKost = kostService.updateKost(id, kostDTO);
        return ResponseEntity.ok(updatedKost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKost(@PathVariable Long id) {
        kostService.deleteKost(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/nearby")
    public ResponseEntity<Page<Kost>> getNearbyKosts(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam Double radius,
            Pageable pageable) {
        Page<Kost> nearbyKosts = kostService.findNearbyKosts(latitude, longitude, radius, pageable);
        return ResponseEntity.ok(nearbyKosts);
    }

    @GetMapping("/by-university/{universityId}")
    public ResponseEntity<Page<Kost>> getKostsByUniversity(
            @PathVariable Long universityId,
            Pageable pageable) {
        Page<Kost> kosts = kostService.findKostsByUniversity(universityId, pageable);
        return ResponseEntity.ok(kosts);
    }
}
