package com.anstay.controller;

import com.anstay.dto.ApartmentDTO;
import com.anstay.entity.Apartment;
import com.anstay.enums.Area;
import com.anstay.repository.ApartmentRepository;
import com.anstay.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ApartmentRepository apartmentRepository;

    // Lấy tất cả căn hộ
    @GetMapping
    public ResponseEntity<List<ApartmentDTO>> getAllApartments() {
        return ResponseEntity.ok(apartmentService.getAllApartments());
    }

    // Lấy căn hộ theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApartmentDTO> getApartmentById(@PathVariable Integer id) {
        ApartmentDTO apartment = apartmentService.getApartmentById(id);
        return apartment != null ? ResponseEntity.ok(apartment) : ResponseEntity.notFound().build();
    }

    // Tạo mới căn hộ
    @PostMapping
    public ResponseEntity<ApartmentDTO> createApartment(@RequestBody ApartmentDTO apartmentDTO) {
        ApartmentDTO savedApartment = apartmentService.createApartment(apartmentDTO);
        return savedApartment != null ? ResponseEntity.ok(savedApartment) : ResponseEntity.badRequest().build();
    }

    // Cập nhật căn hộ
    @PutMapping("/{id}")
    public ResponseEntity<ApartmentDTO> updateApartment(@PathVariable Integer id, @RequestBody ApartmentDTO apartmentDTO) {
        ApartmentDTO updatedApartment = apartmentService.updateApartment(id, apartmentDTO);
        return updatedApartment != null ? ResponseEntity.ok(updatedApartment) : ResponseEntity.notFound().build();
    }

    // Xóa căn hộ
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApartment(@PathVariable Integer id) {
        return apartmentService.deleteApartment(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
    // In ApartmentController.java
    @GetMapping("/by-area")
    public ResponseEntity<List<ApartmentDTO>> getApartmentsByArea(@RequestParam Area area) {
        try {
            List<ApartmentDTO> apartments = apartmentService.getApartmentsByArea(area);
            return ResponseEntity.ok(apartments);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }
}
