package com.anstay.controller;

import com.anstay.dto.TourImageDTO;
import com.anstay.service.TourImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tour-images")
public class TourImageController {

    @Autowired
    private TourImageService tourImageService;

    // 🟢 Lấy danh sách ảnh theo tour_id
    @GetMapping("/{tourId}")
    public ResponseEntity<List<TourImageDTO>> getImagesByTourId(@PathVariable Integer tourId) {
        List<TourImageDTO> images = tourImageService.getImagesByTourId(tourId);
        return ResponseEntity.ok(images);
    }

    // 🟢 Thêm ảnh mới
    @PostMapping
    public ResponseEntity<TourImageDTO> addTourImage(@RequestBody TourImageDTO tourImageDTO) {
        TourImageDTO savedImage = tourImageService.addTourImage(tourImageDTO);
        if (savedImage != null) {
            return ResponseEntity.ok(savedImage);
        }
        return ResponseEntity.badRequest().build();
    }

    // 🟢 Xóa ảnh theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTourImage(@PathVariable Integer id) {
        boolean isDeleted = tourImageService.deleteTourImage(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
    @PatchMapping("/{id}/isFeatured")
    public ResponseEntity<Void> toggleIsFeatured(@PathVariable Integer id) {
        boolean isUpdated = tourImageService.toggleIsFeatured(id);
        return isUpdated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
