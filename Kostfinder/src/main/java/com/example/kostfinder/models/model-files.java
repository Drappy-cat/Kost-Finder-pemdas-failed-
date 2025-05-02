// 1. User.java
package com.kostfinder.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "student_id", nullable = false, unique = true)
    private String studentId;

    @Column(name = "university_id")
    private Long universityId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    @Column(name = "verification_token")
    private String verificationToken;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRole> roles = new HashSet<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

// 2. Kost.java
package com.kostfinder.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kosts")
public class Kost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String address;

    private Double latitude;
    private Double longitude;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "total_rooms")
    private Integer totalRooms;

    @Column(name = "available_rooms")
    private Integer availableRooms;

    @ManyToMany
    @JoinTable(
        name = "kost_facilities",
        joinColumns = @JoinColumn(name = "kost_id"),
        inverseJoinColumns = @JoinColumn(name = "facility_id")
    )
    private Set<Fasilitas> facilities = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "kost_rules", joinColumns = @JoinColumn(name = "kost_id"))
    @Column(name = "rule")
    private List<String> rules = new ArrayList<>();

    @Column(name = "owner_phone")
    private String ownerPhone;

    private Boolean wifi = false;
    private Boolean ac = false;

    @Column(nullable = false)
    private String gender; // MALE, FEMALE, MIXED

    @Column(name = "owner_id")
    private Long ownerId;

    @OneToMany(mappedBy = "kost", cascade = CascadeType.ALL)
    private List<FotoKost> photos = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

// 3. Reservasi.java
package com.kostfinder.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservasi")
public class Reservasi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "kost_id", nullable = false)
    private Kost kost;

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

    @Column(name = "duration_type")
    private String durationType; // MONTHLY, YEARLY

    @Column(name = "payment_status")
    private String paymentStatus; // PENDING, PAID, CANCELLED

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    private String status; // PENDING, CONFIRMED, CANCELLED

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

// 4. Universitas.java
package com.kostfinder.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "universitas")
public class Universitas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private Double latitude;
    private Double longitude;

    private String website;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "universityId")
    private List<User> students;
}

// 5. UserRole.java
package com.kostfinder.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true)
    private String name; // ROLE_USER, ROLE_ADMIN

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}

// 6. Fasilitas.java
package com.kostfinder.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fasilitas")
public class Fasilitas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "is_common")
    private Boolean isCommon = false;

    @ManyToMany(mappedBy = "facilities")
    private Set<Kost> kosts;
}

// 7. FotoKost.java
package com.kostfinder.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "foto_kost")
public class FotoKost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kost_id", nullable = false)
    private Kost kost;

    @Column(nullable = false)
    private String url;

    private String caption;

    @Column(name = "is_main")
    private Boolean isMain = false;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_size")
    private Long fileSize;
}
