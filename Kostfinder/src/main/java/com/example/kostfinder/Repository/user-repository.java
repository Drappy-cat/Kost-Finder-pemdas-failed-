package com.example.kostfinder.repository;

import com.example.kostfinder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    
    Optional<User> findByUsername(String username);
    
    Boolean existsByEmail(String email);
    
    Boolean existsByUsername(String username);
    
    Optional<User> findByEmailAndIsVerifiedTrue(String email);
    
    @Query("SELECT u FROM User u WHERE u.universityId = :universityId")
    List<User> findByUniversityId(@Param("universityId") Long universityId);
    
    @Query("SELECT u FROM User u WHERE u.studentId = :studentId")
    Optional<User> findByStudentId(@Param("studentId") String studentId);
    
    List<User> findByIsVerifiedFalse();
    
    @Query("SELECT u FROM User u WHERE LOWER(u.fullName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<User> searchByName(@Param("keyword") String keyword);
}
