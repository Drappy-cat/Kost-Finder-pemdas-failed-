// FasilitasRepository.java
package com.kostfinder.repository;

import com.kostfinder.model.Fasilitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FasilitasRepository extends JpaRepository<Fasilitas, Long> {
    List<Fasilitas> findByIsCommon(Boolean isCommon);
}

// UniversitasRepository.java
package com.kostfinder.repository;

import com.kostfinder.model.Universitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UniversitasRepository extends JpaRepository<Universitas, Long> {
    @Query(value = "SELECT * FROM universitas u " +
           "WHERE ST_Distance_Sphere(" +
           "    point(u.longitude, u.latitude), " +
           "    point(:longitude, :latitude)" +
           ") <= :radius", nativeQuery = true)
    List<Universitas> findNearbyUniversities(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude,
        @Param("radius") Double radius
    );
}

// FotoKostRepository.java
package com.kostfinder.repository;

import com.kostfinder.model.FotoKost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FotoKostRepository extends JpaRepository<FotoKost, Long> {
    List<FotoKost> findByKostId(Long kostId);
    
    Optional<FotoKost> findByKostIdAndIsMainTrue(Long kostId);
    
    void deleteByKostId(Long kostId);
}

// UserRoleRepository.java
package com.kostfinder.repository;

import com.kostfinder.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByName(String name);
}
