package com.kostfinder.kostfinder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    // Membuat bean JdbcTemplate yang akan diakses oleh Spring
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        // Membuat dan mengonfigurasi JdbcTemplate dengan DataSource
        return new JdbcTemplate(dataSource);
    }
}