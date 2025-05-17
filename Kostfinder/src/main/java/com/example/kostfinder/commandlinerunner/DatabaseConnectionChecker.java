package com.kostfinder.kostfinder.commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionChecker implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    // Konstruktor untuk meng-inject JdbcTemplate
    public DatabaseConnectionChecker(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            // Cek koneksi dengan query sederhana
            String sql = "SELECT 1";
            Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
            
            if (result != null && result == 1) {
                System.out.println("Koneksi ke database berhasil!");
            } else {
                System.out.println("Koneksi ke database gagal!");
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menghubungkan ke database: " + e.getMessage());
        }
    }
}
