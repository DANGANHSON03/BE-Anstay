package com.anstay.dto;

import com.anstay.enums.BookingType;
import com.anstay.enums.PaymentMethod;
import com.anstay.enums.PaymentStatus;

import java.util.Date;

public class PaymentDTO {
    private Integer id; // ✅ Bổ sung ID
    private BookingType bookingType;
    private Integer bookingId;
    private Integer userId;
    private String userFullName;
    private Double amount;
    private PaymentMethod paymentMethod;
    private String transactionId;
    private PaymentStatus status;
    private Date createdAt;

    public PaymentDTO(Integer id, BookingType bookingType, Integer bookingId, Integer userId, String userFullName, Double amount, PaymentMethod paymentMethod, String transactionId, PaymentStatus status, Date createdAt) {
        this.id = id;
        this.bookingType = bookingType;
        this.bookingId = bookingId;
        this.userId = userId;
        this.userFullName = userFullName;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookingType getBookingType() {
        return bookingType;
    }

    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
