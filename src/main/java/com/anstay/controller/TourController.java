package com.anstay.controller;

import com.anstay.dto.TourDTO;
import com.anstay.entity.Tour;
import com.anstay.enums.Area;
import com.anstay.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
        System.out.println("🔍 API được gọi với id: " + id);
        TourDTO tourDTO = tourService.getTourById(id);
        System.out.println("🔍 Tour tìm thấy: " + (tourDTO != null ? tourDTO.toString() : "null"));

        if (tourDTO != null) {
            return ResponseEntity.ok(tourDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/by-area")
    public ResponseEntity<List<TourDTO>> getToursByArea(@RequestParam Area area) {
        try {
            List<TourDTO> tours = tourService.getAllToursByArea(area);
            return ResponseEntity.ok(tours);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }
}
