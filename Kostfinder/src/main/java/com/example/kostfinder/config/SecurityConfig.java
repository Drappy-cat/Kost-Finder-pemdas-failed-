package com.example.kostfinder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Konfigurasi Security Filter Chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Nonaktifkan CSRF untuk development
            .csrf(AbstractHttpConfigurer::disable)
            
            // Atur manajemen session tanpa state
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // Konfigurasi otorisasi request
            .authorizeHttpRequests(authorize -> authorize
                // Endpoint publik
                .requestMatchers("/api/user/register", "/api/user/login").permitAll()
                
                // Endpoint yang membutuhkan autentikasi
                .requestMatchers("/api/kost/**").authenticated()
                
                // Semua request lain membutuhkan autentikasi
                .anyRequest().authenticated()
            );

        return http.build();
    }

    // Bean untuk enkripsi password
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt dengan strength factor 12
        return new BCryptPasswordEncoder(12);
    }

    // Konfigurasi Web Security (opsional)
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            // Abaikan request ke static resources
            .requestMatchers("/css/**", "/js/**", "/img/**");
    }
}