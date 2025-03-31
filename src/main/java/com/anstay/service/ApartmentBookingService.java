package com.anstay.service;

import com.anstay.dto.ApartmentBookingDTO;
import com.anstay.entity.Apartment;
import com.anstay.entity.ApartmentBooking;
import com.anstay.entity.User;
import com.anstay.enums.BookingStatus;
import com.anstay.repository.ApartmentBookingRepository;
import com.anstay.repository.ApartmentRepository;
import com.anstay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApartmentBookingService {

    @Autowired
    private ApartmentBookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    // Lấy tất cả booking
    public List<ApartmentBookingDTO> getAllBookings() {
        List<ApartmentBooking> bookings = bookingRepository.findAll();
        return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Lấy booking theo ID
    public ApartmentBookingDTO getBookingById(Integer id) {
        Optional<ApartmentBooking> booking = bookingRepository.findById(id);
        return booking.map(this::convertToDTO).orElse(null);
    }

    // Tạo booking mới
    public ApartmentBookingDTO createBooking(ApartmentBookingDTO bookingDTO) {
        Optional<User> userOpt = userRepository.findById(bookingDTO.getUserId());
        Optional<Apartment> apartmentOpt = apartmentRepository.findById(bookingDTO.getApartmentId());

        if (userOpt.isPresent() && apartmentOpt.isPresent()) {
            ApartmentBooking booking = new ApartmentBooking(
                    userOpt.get(),
                    apartmentOpt.get(),
                    bookingDTO.getCheckIn(),
                    bookingDTO.getCheckOut(),
                    bookingDTO.getTotalPrice(),
                    BookingStatus.PENDING
            );

            ApartmentBooking savedBooking = bookingRepository.save(booking);
            return convertToDTO(savedBooking);
        }
        return null;
    }

    // Cập nhật booking
    public ApartmentBookingDTO updateBooking(Integer id, ApartmentBookingDTO bookingDTO) {
        Optional<ApartmentBooking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            ApartmentBooking booking = bookingOpt.get();

            Optional<User> userOpt = userRepository.findById(bookingDTO.getUserId());
            Optional<Apartment> apartmentOpt = apartmentRepository.findById(bookingDTO.getApartmentId());

            if (userOpt.isPresent() && apartmentOpt.isPresent()) {
                booking.setUser(userOpt.get());
                booking.setApartment(apartmentOpt.get());
                booking.setCheckIn(bookingDTO.getCheckIn());
                booking.setCheckOut(bookingDTO.getCheckOut());
                booking.setTotalPrice(bookingDTO.getTotalPrice());
                booking.setStatus(bookingDTO.getStatus());

                ApartmentBooking updatedBooking = bookingRepository.save(booking);
                return convertToDTO(updatedBooking);
            }
        }
        return null;
    }

    // Xóa booking
    public boolean deleteBooking(Integer id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Chuyển đổi entity -> DTO
    private ApartmentBookingDTO convertToDTO(ApartmentBooking booking) {
        return new ApartmentBookingDTO(
                booking.getId(),
                booking.getUser().getId(),
                booking.getApartment().getId(),
                booking.getCheckIn(),
                booking.getCheckOut(),
                booking.getTotalPrice(),
                booking.getStatus()
        );
    }
}
