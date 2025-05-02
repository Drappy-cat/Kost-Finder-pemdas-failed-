package com.example.kostfinder.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.kostfinder.Repository.KostRepository;
import com.example.kostfinder.models.Kost;

@Service
public class KostService {

    @Autowired
    private KostRepository kostRepository;

    // Menambahkan data kost baru
    public Kost addKost(Kost kost) {
        return kostRepository.save(kost); // Simpan data kost ke database
    }

    // Memperbarui data kost berdasarkan ID
    public Kost updateKost(Long id, Kost updatedKost) {
        // Cari data kost berdasarkan ID
        Optional<Kost> existingKost = kostRepository.findById(id);
        if (existingKost.isPresent()) {
            Kost kost = existingKost.get();
            // Update field yang ingin diperbarui
            kost.setName(updatedKost.getName());
            kost.setLocation(updatedKost.getLocation());
            kost.setPrice(updatedKost.getName());
            kost.setFacilities(updatedKost.getFacilities());
            return kostRepository.save(kost); // Simpan data yang telah diperbarui
        }
        return null; // Jika ID tidak ditemukan, kembalikan null
    }

    // Menghapus data kost berdasarkan ID
    public void deleteKost(Long id) {
        if (kostRepository.existsById(id)) {
            kostRepository.deleteById(id); // Hapus data kost berdasarkan ID
        }
    }

    // Mendapatkan data kost berdasarkan ID
    public Optional<Kost> getKostById(Long id) {
        return kostRepository.findById(id); // Cari kost berdasarkan ID
    }

    // Mendapatkan semua data kost
    public List<Kost> getAllKosts() {
        return kostRepository.findAll(); // Mengembalikan daftar semua kost
    }
}
