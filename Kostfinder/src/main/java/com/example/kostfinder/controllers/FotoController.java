package com.example.kostfinder.controller;

import com.example.kostfinder.models.FotoKost;
import com.example.kostfinder.services.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/fotos")
public class FotoController {

    @Autowired
    private FotoService fotoService;

    @PostMapping("/upload/{kostId}")
    public ResponseEntity<FotoKost> uploadFoto(
            @PathVariable Long kostId,
            @RequestParam("file") MultipartFile file) {
        FotoKost foto = fotoService.saveFoto(kostId, file);
        return ResponseEntity.ok(foto);
    }

    @GetMapping("/kost/{kostId}")
    public ResponseEntity<List<FotoKost>> getKostFotos(@PathVariable Long kostId) {
        List<FotoKost> fotos = fotoService.getKostFotos(kostId);
        return ResponseEntity.ok(fotos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFoto(@PathVariable Long id) {
        fotoService.deleteFoto(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/main")
    public ResponseEntity<?> setMainFoto(@PathVariable Long id) {
        fotoService.setMainFoto(id);
        return ResponseEntity.ok().build();
    }
}
