package com.anstay.controller;

import com.anstay.dto.ApartmentImageDTO;
import com.anstay.service.ApartmentImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apartment-images")
public class ApartmentImageController {

    @Autowired
    private ApartmentImageService apartmentImageService;

    // Lấy tất cả ảnh có cùng apartment_id
    @GetMapping
    public ResponseEntity<List<ApartmentImageDTO>> getImagesByApartmentId(@RequestParam("apartmentId") Integer apartmentId) {
        List<ApartmentImageDTO> images = apartmentImageService.getImagesByApartmentId(apartmentId);
        return images.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(images);
    }

    // Lấy ảnh theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApartmentImageDTO> getImageById(@PathVariable Integer id) {
        ApartmentImageDTO image = apartmentImageService.getImageById(id);
        return image != null ? ResponseEntity.ok(image) : ResponseEntity.notFound().build();
    }

    // Tạo ảnh mới
    @PostMapping
    public ResponseEntity<ApartmentImageDTO> createImage(@RequestBody ApartmentImageDTO imageDTO) {
        ApartmentImageDTO savedImage = apartmentImageService.createImage(imageDTO);
        return savedImage != null ? ResponseEntity.ok(savedImage) : ResponseEntity.badRequest().build();
    }

    // Cập nhật ảnh
    @PutMapping("/{id}")
    public ResponseEntity<ApartmentImageDTO> updateImage(@PathVariable Integer id, @RequestBody ApartmentImageDTO imageDTO) {
        ApartmentImageDTO updatedImage = apartmentImageService.updateImage(id, imageDTO);
        return updatedImage != null ? ResponseEntity.ok(updatedImage) : ResponseEntity.notFound().build();
    }

    // Xóa ảnh
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Integer id) {
        return apartmentImageService.deleteImage(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
