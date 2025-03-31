package com.anstay.repository;

import com.anstay.entity.ApartmentBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApartmentBookingRepository extends JpaRepository<ApartmentBooking, Integer> {
    List<ApartmentBooking> findByUserId(Integer userId);
    List<ApartmentBooking> findByApartmentId(Integer apartmentId);

    @Query("SELECT COUNT(a) FROM ApartmentBooking a")
    long countTotalBookings();

    @Query("SELECT SUM(a.totalPrice) FROM ApartmentBooking a")
    Double getTotalRevenue();


}
