package com.anstay.service;

import com.anstay.dto.PaymentDTO;
import com.anstay.entity.Payment;
import com.anstay.entity.User;
import com.anstay.repository.PaymentRepository;
import com.anstay.repository.UserRepository;
import com.anstay.service.PaymentService;
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
        User user = userRepository.findById(paymentDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        payment.setUser(user);
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setStatus(paymentDTO.getStatus());

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
        return new PaymentDTO(
                payment.getId(),
                payment.getBookingType(),
                payment.getBookingId(),
                payment.getUser().getId(),  // Get user ID from User entity
                payment.getUser().getFullName(),  // Get user full name
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getTransactionId(),
                payment.getStatus(),
                payment.getCreatedAt()
        );
    }
}
