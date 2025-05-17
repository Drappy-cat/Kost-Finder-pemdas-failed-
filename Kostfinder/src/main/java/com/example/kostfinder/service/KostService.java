package com.kostfinder.kostfinder.service;

import com.kostfinder.kostfinder.models.Kost;
import com.kostfinder.kostfinder.exceptions.KostNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class KostService {

    private static final Logger logger = LoggerFactory.getLogger(KostService.class);

    private final JdbcTemplate jdbcTemplate; // JdbcTemplate diubah menjadi final untuk digunakan di constructor

    // RowMapper untuk mapping data dari database ke objek Kost
    private final RowMapper<Kost> kostRowMapper = (rs, rowNum) -> {
        Kost kost = new Kost();
        kost.setId(rs.getLong("id"));
        kost.setName(rs.getString("name"));
        kost.setDescription(rs.getString("description"));
        kost.setFacilities(rs.getString("facilities"));
        kost.setPrice(rs.getInt("price"));
        kost.setOwnerContact(rs.getString("owner_contact"));
        
        // Mengambil gambar dari tabel 'kost_images'
        String images = rs.getString("image_path");
        kost.setImagePaths(images != null ? Arrays.asList(images.split(",")) : new ArrayList<>());
        return kost;
    };

    // Constructor injection untuk JdbcTemplate
    public KostService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Menyimpan kost baru ke database
     */
    @Transactional
    public Kost saveKost(Kost kost) {
        try {
            // Validasi harga kost
            if (kost.getPrice() <= 0) {
                logger.error("Harga kost tidak boleh negatif atau nol: {}", kost.getPrice());
                throw new IllegalArgumentException("Harga kost harus lebih besar dari 0");
            }

            // Menyimpan kost ke database
            String sql = "INSERT INTO kost_new (name, description, facilities, price, owner_contact) " +
                         "VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, kost.getName(), kost.getDescription(), kost.getFacilities(), kost.getPrice(), kost.getOwnerContact());

            // Ambil ID yang baru saja disimpan (untuk penggunaan di tabel gambar)
            Long kostId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

            // Menyimpan gambar ke tabel 'kost_images' (Menggunakan One-to-Many)
            if (kost.getImagePaths() != null && !kost.getImagePaths().isEmpty()) {
                for (String imagePath : kost.getImagePaths()) {
                    String imageSql = "INSERT INTO kost_images (kost_id, image_path) VALUES (?, ?)";
                    jdbcTemplate.update(imageSql, kostId, imagePath);
                }
            }

            logger.info("Kost dengan nama {} berhasil disimpan", kost.getName());
            return kost;  // Kembalikan kost yang baru disimpan
        } catch (Exception e) {
            logger.error("Terjadi kesalahan saat menyimpan data kost: ", e);
            throw new RuntimeException("Error saat menyimpan data kost", e);
        }
    }

    /**
     * Mengambil semua data kost dari database
     */
    public List<Kost> getAllKost() {
        try {
            // Menggunakan JOIN untuk menggabungkan data dari tabel 'kost' dan 'kost_images'
            String sql = "SELECT k.id, k.name, k.description, k.facilities, k.price, k.owner_contact, GROUP_CONCAT(ki.image_path) AS image_path " +
                         "FROM kost_new k LEFT JOIN kost_images ki ON k.id = ki.kost_id " +
                         "GROUP BY k.id";
            return jdbcTemplate.query(sql, kostRowMapper);
        } catch (Exception e) {
            logger.error("Terjadi kesalahan saat mengambil daftar kost: ", e);
            throw new RuntimeException("Error saat mengambil daftar kost", e);
        }
    }

    /**
     * Mencari kost berdasarkan ID
     */
    public Kost getKostById(Long id) {
        try {
            // Menggunakan JOIN untuk mendapatkan data kost dan gambar berdasarkan ID
            String sql = "SELECT k.id, k.name, k.description, k.facilities, k.price, k.owner_contact, GROUP_CONCAT(ki.image_path) AS image_path " +
                         "FROM kost_new k LEFT JOIN kost_images ki ON k.id = ki.kost_id " +
                         "WHERE k.id = ? " +
                         "GROUP BY k.id";
            Kost kost = jdbcTemplate.queryForObject(sql, kostRowMapper, id);
            
            if (kost == null) {
                throw new KostNotFoundException("Kost dengan ID " + id + " tidak ditemukan");
            }
            
            return kost;
        } catch (Exception e) {
            logger.error("Kost dengan ID {} tidak ditemukan", id, e);
            throw new KostNotFoundException("Kost dengan ID " + id + " tidak ditemukan");
        }
    }

    /**
     * Mencari kost berdasarkan nama
     */
    public List<Kost> findKostByName(String name) {
        try {
            String sql = "SELECT * FROM kost_new k LEFT JOIN kost_images ki ON k.id = ki.kost_id WHERE k.name LIKE ?";
            return jdbcTemplate.query(sql, kostRowMapper, "%" + name + "%");
        } catch (Exception e) {
            logger.error("Terjadi kesalahan saat mencari kost berdasarkan nama: {}", name, e);
            throw new RuntimeException("Error saat mencari kost berdasarkan nama", e);
        }
    }

    /**
     * Mencari kost berdasarkan harga
     */
    public List<Kost> findKostByPrice(int price) {
        try {
            String sql = "SELECT * FROM kost_new k LEFT JOIN kost_images ki ON k.id = ki.kost_id WHERE k.price <= ?";
            return jdbcTemplate.query(sql, kostRowMapper, price);
        } catch (Exception e) {
            logger.error("Terjadi kesalahan saat mencari kost berdasarkan harga: {}", price, e);
            throw new RuntimeException("Error saat mencari kost berdasarkan harga", e);
        }
    }

    /**
     * Mencari kost berdasarkan fasilitas
     */
    public List<Kost> findKostByFacilities(String facilities) {
        try {
            String sql = "SELECT * FROM kost_new k LEFT JOIN kost_images ki ON k.id = ki.kost_id WHERE k.facilities LIKE ?";
            return jdbcTemplate.query(sql, kostRowMapper, "%" + facilities + "%");
        } catch (Exception e) {
            logger.error("Terjadi kesalahan saat mencari kost berdasarkan fasilitas: {}", facilities, e);
            throw new RuntimeException("Error saat mencari kost berdasarkan fasilitas", e);
        }
    }
}
