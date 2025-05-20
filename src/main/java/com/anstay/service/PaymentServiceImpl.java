package com.anstay.service;

import com.anstay.dto.PaymentDTO;
import com.anstay.entity.Payment;
import com.anstay.entity.User;
import com.anstay.repository.PaymentRepository;
import com.anstay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setBookingType(paymentDTO.getBookingType());
        payment.setBookingId(paymentDTO.getBookingId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setStatus(paymentDTO.getStatus());

        // Xử lý user thật hoặc khách vãng lai
        if (paymentDTO.getUserId() != null) {
            User user = userRepository.findById(paymentDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            payment.setUser(user);

            // Dữ liệu guest để null
            payment.setGuestName(null);
            payment.setGuestPhone(null);
            payment.setGuestEmail(null);
            payment.setGuestIdentityNumber(null);
            payment.setGuestBirthday(null);
            payment.setGuestNationality(null);

        } else {
            payment.setUser(null);
            payment.setGuestName(paymentDTO.getGuestName());
            payment.setGuestPhone(paymentDTO.getGuestPhone());
            payment.setGuestEmail(paymentDTO.getGuestEmail());
            payment.setGuestIdentityNumber(paymentDTO.getGuestIdentityNumber());
            payment.setGuestBirthday(paymentDTO.getGuestBirthday());
            payment.setGuestNationality(paymentDTO.getGuestNationality());
        }

        Payment savedPayment = paymentRepository.save(payment);
        return convertToDTO(savedPayment);
    }

    @Override
    public PaymentDTO getPaymentById(Integer id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
        return convertToDTO(payment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private PaymentDTO convertToDTO(Payment payment) {
        Integer userId = null;
        String userFullName = null;

        if (payment.getUser() != null) {
            userId = payment.getUser().getId();
            userFullName = payment.getUser().getFullName();
        }

        return new PaymentDTO(
                payment.getId(),
                payment.getBookingType(),
                payment.getBookingId(),
                userId,
                userFullName,
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getTransactionId(),
                payment.getStatus(),
                payment.getCreatedAt(),
                payment.getGuestName(),
                payment.getGuestPhone(),
                payment.getGuestEmail(),
                payment.getGuestIdentityNumber(),
                payment.getGuestBirthday(),
                payment.getGuestNationality()
        );
    }
}
