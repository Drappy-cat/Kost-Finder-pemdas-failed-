package com.example.kostfinder.Repository;

import com.example.kostfinder.models.Kost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface KostRepository extends JpaRepository<Kost, Long> {
    List<Kost> findByOwnerId(Long ownerId);
    
    @Query("SELECT k FROM Kost k WHERE k.price <= :maxPrice")
    List<Kost> findByPriceLessThanEqual(@Param("maxPrice") BigDecimal maxPrice);
    
    @Query("SELECT k FROM Kost k WHERE k.availableRooms > 0")
    List<Kost> findAvailableKosts();
    
    @Query(value = "SELECT * FROM kost k " +
           "WHERE ST_Distance_Sphere(" +
           "    point(k.longitude, k.latitude), " +
           "    point(:longitude, :latitude)" +
           ") <= :radius", nativeQuery = true)
    List<Kost> findNearbyKosts(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude,
        @Param("radius") Double radius
    );
    
    @Query("SELECT k FROM Kost k WHERE " +
           "LOWER(k.address) LIKE LOWER(CONCAT('%', :location, '%')) " +
           "AND (:maxPrice IS NULL OR k.price <= :maxPrice) " +
           "AND (:gender IS NULL OR k.gender = :gender)")
    Page<Kost> searchKosts(
        @Param("location") String location,
        @Param("maxPrice") BigDecimal maxPrice,
        @Param("gender") String gender,
        Pageable pageable
    );
    
    List<Kost> findByGender(String gender);
    
    @Query("SELECT k FROM Kost k WHERE k.wifi = true")
    List<Kost> findKostsWithWifi();
    
    @Query("SELECT k FROM Kost k WHERE k.ac = true")
    List<Kost> findKostsWithAC();
}
