package com.anstay.controller;

import com.anstay.dto.ApartmentBookingDTO;
import com.anstay.service.ApartmentBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apartment-bookings")
public class ApartmentBookingController {

    @Autowired
    private ApartmentBookingService bookingService;

    // Lấy tất cả bookingđbgdbgbg
    @GetMapping
    public ResponseEntity<List<ApartmentBookingDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // Lấy booking theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApartmentBookingDTO> getBookingById(@PathVariable Integer id) {
        ApartmentBookingDTO booking = bookingService.getBookingById(id);
        return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
    }

    // Tạo booking mới
    @PostMapping
    public ResponseEntity<ApartmentBookingDTO> createBooking(@RequestBody ApartmentBookingDTO bookingDTO) {
        ApartmentBookingDTO savedBooking = bookingService.createBooking(bookingDTO);
        return savedBooking != null ? ResponseEntity.ok(savedBooking) : ResponseEntity.badRequest().build();
    }

    // Cập nhật booking
    @PutMapping("/{id}")
    public ResponseEntity<ApartmentBookingDTO> updateBooking(@PathVariable Integer id, @RequestBody ApartmentBookingDTO bookingDTO) {
        ApartmentBookingDTO updatedBooking = bookingService.updateBooking(id, bookingDTO);
        return updatedBooking != null ? ResponseEntity.ok(updatedBooking) : ResponseEntity.notFound().build();
    }

    // Xóa booking
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Integer id) {
        return bookingService.deleteBooking(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
