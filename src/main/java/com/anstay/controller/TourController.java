package com.anstay.controller;

import com.anstay.dto.TourDTO;
import com.anstay.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
public class TourController {

    @Autowired
    private TourService tourService;

    // 🟢 API lấy danh sách tất cả Tour
    @GetMapping
    public ResponseEntity<List<TourDTO>> getAllTours() {
        List<TourDTO> tours = tourService.getAllTours();
        return ResponseEntity.ok(tours);
    }

    // 🟢 API lấy thông tin 1 Tour theo ID
    @GetMapping("/{id}")
    public ResponseEntity<TourDTO> getTourById(@PathVariable Integer id) {
        TourDTO tourDTO = tourService.getTourById(id);
        if (tourDTO != null) {
            return ResponseEntity.ok(tourDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
