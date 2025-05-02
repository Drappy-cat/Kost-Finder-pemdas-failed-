package com.example.kostfinder.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Konfigurasi database
    private static final String URL = "jdbc:mysql://localhost:3306/nama_database"; // Sesuaikan dengan URL database Anda
    private static final String USER = "username"; // Sesuaikan dengan username database Anda
    private static final String PASSWORD = "password"; // Sesuaikan dengan password database Anda

    public static Connection getConnection() {
        try {
            // Mengembalikan koneksi database
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
}