package com.anstay.dto;

import com.anstay.enums.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ApartmentBookingDTO {
    private Integer id;
    private Integer userId;
    private Integer apartmentId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal totalPrice;
    private BookingStatus status;

    public ApartmentBookingDTO(Integer id, Integer userId, Integer apartmentId, LocalDate checkIn, LocalDate checkOut, BigDecimal totalPrice, BookingStatus status) {
        this.id = id;
        this.userId = userId;
        this.apartmentId = apartmentId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
        this.status = status;
    }

// Getters and Setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
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

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}