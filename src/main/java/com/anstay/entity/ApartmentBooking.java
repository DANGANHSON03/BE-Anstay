package com.anstay.entity;

import com.anstay.enums.BookingStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "apartment_bookings")
public class ApartmentBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;

    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.PENDING;

    // Constructors
    public ApartmentBooking() {}

    public ApartmentBooking(User user, Apartment apartment, LocalDate checkIn, LocalDate checkOut, BigDecimal totalPrice, BookingStatus status) {
        this.user = user;
        this.apartment = apartment;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public BookingStatus getStatus() {
        return status;
    }
}
