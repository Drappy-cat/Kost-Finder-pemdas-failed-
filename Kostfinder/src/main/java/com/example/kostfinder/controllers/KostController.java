package com.kostfinder.kostfinder.controllers;

import com.kostfinder.kostfinder.models.Kost;
import com.kostfinder.kostfinder.service.KostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class KostController {

    private final KostService kostService;

    // Konstanta untuk menghindari literal yang berulang
    private static final String ERROR_MESSAGE = "error";
    private static final String ADD_KOST_PAGE = "add-kost";
    private static final String UPLOAD_DIR = "uploads/";

    public KostController(KostService kostService) {
        this.kostService = kostService; // Constructor injection
    }

    @GetMapping("/")
    public String showIndexPage(Model model) {
        model.addAttribute("message", "Selamat datang di Kost Finder!");
        return "index";
    }

    @GetMapping("/kost/detail/{id}")
    public String viewKostDetail(@PathVariable Long id, Model model) {
        Kost kost = kostService.getKostById(id);
        if (kost != null) {
            model.addAttribute("kost", kost);
            return "kost-detail";
        } else {
            model.addAttribute(ERROR_MESSAGE, "Data kost tidak ditemukan!");  // Menggunakan konstanta ERROR_MESSAGE
            return ERROR_MESSAGE;  // Menggunakan halaman error
        }
    }

    @GetMapping("/add-kost")
    public String showAddKostForm() {
        return ADD_KOST_PAGE;
    }

    @PostMapping("/add-kost")
    public String addKost(@RequestParam("name") String name,
                          @RequestParam("description") String description,
                          @RequestParam("facilities") String facilities,
                          @RequestParam("price") int price,
                          @RequestParam("owner_contact") String ownerContact,
                          @RequestParam("images") MultipartFile[] images,
                          Model model) {

        // Validasi input
        if (isInvalidInput(name, description, ownerContact, price, model)) {
            return ADD_KOST_PAGE;
        }

        // Menyimpan gambar
        List<String> imagePaths = saveImages(images, model);
        if (imagePaths.isEmpty()) {
            return ADD_KOST_PAGE; // Jika ada masalah dengan gambar
        }

        // Membuat dan menyimpan objek kost
        Kost kost = new Kost(name, description, facilities, price, ownerContact, imagePaths);
        if (!saveKost(kost, model)) {
            return ADD_KOST_PAGE;
        }

        model.addAttribute("successMessage", "Kost berhasil ditambahkan!");
        return "redirect:/kost/list";  // Redirect ke daftar kost setelah berhasil
    }

    private boolean isInvalidInput(String name, String description, String ownerContact, int price, Model model) {
        if (name.isEmpty() || description.isEmpty() || ownerContact.isEmpty()) {
            model.addAttribute(ERROR_MESSAGE, "Semua kolom harus diisi!");  // Menggunakan konstanta ERROR_MESSAGE
            return true;
        }
        if (price <= 0) {
            model.addAttribute(ERROR_MESSAGE, "Harga kost harus lebih dari 0!");  // Menggunakan konstanta ERROR_MESSAGE
            return true;
        }
        return false;
    }

    private List<String> saveImages(MultipartFile[] images, Model model) {
        List<String> imagePaths = new ArrayList<>();
        if (images != null && images.length > 0) {
            for (MultipartFile image : images) {
                if (!image.isEmpty() && isImageFile(image)) {
                    try {
                        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                        File imageFile = new File(UPLOAD_DIR + fileName);
                        image.transferTo(imageFile);
                        imagePaths.add("/" + UPLOAD_DIR + fileName);
                    } catch (IOException e) {
                        model.addAttribute(ERROR_MESSAGE, "Gagal mengunggah file: " + image.getOriginalFilename());  // Menggunakan konstanta ERROR_MESSAGE
                        return new ArrayList<>(); // Mengembalikan koleksi kosong jika ada error
                    }
                } else {
                    model.addAttribute(ERROR_MESSAGE, "File " + image.getOriginalFilename() + " bukan gambar!");  // Menggunakan konstanta ERROR_MESSAGE
                    return new ArrayList<>(); // Mengembalikan koleksi kosong jika file bukan gambar
                }
            }
        }
        return imagePaths;
    }

    private boolean saveKost(Kost kost, Model model) {
        try {
            kostService.saveKost(kost);
            return true;
        } catch (Exception e) {
            model.addAttribute(ERROR_MESSAGE, "Terjadi kesalahan saat menyimpan data kost!");  // Menggunakan konstanta ERROR_MESSAGE
            return false;
        }
    }

    private boolean isImageFile(MultipartFile file) {
        try {
            String mimeType = Files.probeContentType(Paths.get(file.getOriginalFilename()));
            return mimeType != null && mimeType.startsWith("image");
        } catch (IOException e) {
            return false;
        }
    }

    @PostConstruct
    public void init() {
        File uploadFolder = new File(UPLOAD_DIR);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
    }

    @GetMapping("/kost/list")
   public String showKostList(Model model) {
        List<Kost> kostList = kostService.getAllKost();
        if (kostList.isEmpty()) {
            model.addAttribute("errorMessage", "Tidak ada kost yang tersedia.");
        }
        model.addAttribute("kostList", kostList);
        return "kost-list";
    }
}
