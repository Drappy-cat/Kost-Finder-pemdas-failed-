package com.kostfinder.kostfinder.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "kost_new")  // Nama tabel yang digunakan dalam database SQLite
public class Kost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ID sebagai primary key dan tipe Long

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(length = 255)
    private String facilities;

    @Column(nullable = false)
    private int price;  // Menggunakan tipe primitif 'int' jika harga tidak boleh null

    @Column(name = "owner_contact", length = 15)
    private String ownerContact;

    // Menyimpan gambar dalam list string yang berisi path gambar
    @ElementCollection
    @CollectionTable(name = "kost_images", joinColumns = @JoinColumn(name = "kost_id"))
    @Column(name = "image_path")
    private List<String> imagePaths;

    // Konstruktor default diperlukan oleh Hibernate (tanpa parameter)
    public Kost() {
    }

    // Konstruktor dengan semua parameter untuk mempermudah pembuatan objek
    public Kost(String name, String description, String facilities, int price, String ownerContact, List<String> imagePaths) {
        this.name = name;
        this.description = description;
        this.facilities = facilities;
        this.price = price;
        this.ownerContact = ownerContact;
        this.imagePaths = imagePaths;
    }

    // Getter dan Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }
}
