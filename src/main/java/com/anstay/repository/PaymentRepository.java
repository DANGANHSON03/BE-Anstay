package com.anstay.repository;

import com.anstay.entity.Payment;
import com.anstay.enums.PaymentStatus;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findByUserId(Integer userId);

    List<Payment> findByStatus(PaymentStatus status);
    @Query("SELECT SUM(p.amount) FROM Payment p")
    Double getTotalRevenue();

    @Query(value = "SELECT a.area, " +
            "DATE_FORMAT(p.created_at, '%Y-%m') as month, " +
            "COALESCE(SUM(p.amount), 0) as revenue " +
            "FROM payments p " +
            "INNER JOIN apartment_bookings ab ON p.booking_id = ab.id " +
            "INNER JOIN apartments a ON ab.apartment_id = a.id " +
            "WHERE p.status = 'COMPLETED' " +
            "AND p.booking_type = 'APARTMENT' " +
            "GROUP BY a.area, month " +
            "ORDER BY a.area, month", nativeQuery = true)
    List<Object[]> getMonthlyRevenueByApartmentArea();

    @Query(value = "SELECT t.area, " +
            "DATE_FORMAT(p.created_at, '%Y-%m') as month, " +
            "COALESCE(SUM(p.amount), 0) as revenue " +
            "FROM payments p " +
            "INNER JOIN tour_bookings tb ON p.booking_id = tb.id " +
            "INNER JOIN tours t ON tb.tour_id = t.id " +
            "WHERE p.status = 'COMPLETED' " +
            "AND p.booking_type = 'TOUR' " +
            "GROUP BY t.area, month " +
            "ORDER BY t.area, month", nativeQuery = true)
    List<Object[]> getMonthlyRevenueByTourArea();

    @Query(value = """
    SELECT t.area, SUM(p.amount), COUNT(p.id)
    FROM payments p
    INNER JOIN tour_bookings tb ON p.booking_id = tb.id
    INNER JOIN tours t ON tb.tour_id = t.id
    WHERE p.booking_type = 'TOUR'
    AND p.status = 'COMPLETED'
    GROUP BY t.area
    """, nativeQuery = true)
    List<Object[]> getRevenueAndOrdersByTourArea();

    @Query(value = """
    SELECT a.area, SUM(p.amount), COUNT(p.id)
    FROM payments p
    INNER JOIN apartment_bookings ab ON p.booking_id = ab.id
    INNER JOIN apartments a ON ab.apartment_id = a.id
    WHERE p.booking_type = 'APARTMENT'
    AND p.status = 'COMPLETED'
    GROUP BY a.area
    """, nativeQuery = true)
    List<Object[]> getRevenueAndOrdersByApartmentArea();


    @Query(value = "SELECT a.area, SUM(p.amount) " +
            "FROM payments p " +
            "INNER JOIN apartment_bookings ab ON p.booking_id = ab.id " +
            "INNER JOIN apartments a ON ab.apartment_id = a.id " +
            "WHERE p.status = 'COMPLETED' " +
            "AND p.booking_type = 'APARTMENT' " +
            "GROUP BY a.area", nativeQuery = true)
    List<Object[]> getRevenueByApartmentArea();

    @Query(value = "SELECT t.area, SUM(p.amount) " +
            "FROM payments p " +
            "INNER JOIN tour_bookings tb ON p.booking_id = tb.id " +
            "INNER JOIN tours t ON tb.tour_id = t.id " +
            "WHERE p.status = 'COMPLETED' " +
            "AND p.booking_type = 'TOUR' " +
            "GROUP BY t.area", nativeQuery = true)
    List<Object[]> getRevenueByTourArea();
}
