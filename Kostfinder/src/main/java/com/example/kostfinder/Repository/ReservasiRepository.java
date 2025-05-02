package com.example.kostfinder.repository;

import com.example.kostfinder.model.Reservasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservasiRepository extends JpaRepository<Reservasi, Long> {
    List<Reservasi> findByUserId(Long userId);
    
    List<Reservasi> findByKostId(Long kostId);
    
    @Query("SELECT r FROM Reservasi r WHERE r.kostId = :kostId AND " +
           "((r.checkInDate BETWEEN :startDate AND :endDate) OR " +
           "(r.checkOutDate BETWEEN :startDate AND :endDate))")
    List<Reservasi> findOverlappingReservations(
        @Param("kostId") Long kostId,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate
    );
    
    List<Reservasi> findByStatus(String status);
    
    @Query("SELECT r FROM Reservasi r WHERE r.userId = :userId AND r.status = :status")
    List<Reservasi> findByUserIdAndStatus(
        @Param("userId") Long userId,
        @Param("status") String status
    );
    
    @Query("SELECT r FROM Reservasi r WHERE r.paymentStatus = :paymentStatus")
    List<Reservasi> findByPaymentStatus(@Param("paymentStatus") String paymentStatus);
    
    @Query("SELECT COUNT(r) FROM Reservasi r WHERE r.kostId = :kostId AND r.status = 'ACTIVE'")
    Long countActiveReservationsByKostId(@Param("kostId") Long kostId);
}
