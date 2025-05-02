package com.example.kostfinder.services;

import com.example.kostfinder.models.User;
import com.example.kostfinder.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Menambahkan pengguna baru
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // Mengambil data pengguna berdasarkan ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Memvalidasi login pengguna
    public boolean validateUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    // Memperbarui data pengguna
    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        }
        return null;
    }

    // Menghapus pengguna berdasarkan ID
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }
}
