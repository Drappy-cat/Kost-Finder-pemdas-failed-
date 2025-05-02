package com.example.kostfinder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.kostfinder.models.Kost;

public interface KostRepository extends JpaRepository<Kost, Long> {}
