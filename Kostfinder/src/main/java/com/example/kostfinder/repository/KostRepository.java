package com.kostfinder.kostfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kostfinder.kostfinder.models.Kost;

import java.util.List;

@Repository
public interface KostRepository extends JpaRepository<Kost, Long> {

    // Mencari kost berdasarkan harga (metode standar tanpa query kustom)
    List<Kost> findByPriceLessThanEqual(int price);

    // Mencari kost berdasarkan nama (metode standar)
    List<Kost> findByNameContainingIgnoreCase(String name);
    
    // Mencari kost berdasarkan fasilitas (metode standar)
    List<Kost> findByFacilitiesContainingIgnoreCase(String facilities);
}
