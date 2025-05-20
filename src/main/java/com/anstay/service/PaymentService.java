package com.anstay.service;

import com.anstay.dto.PaymentDTO;
import java.util.List;

public interface PaymentService {
    PaymentDTO createPayment(PaymentDTO paymentDTO);
    PaymentDTO getPaymentById(Integer id);
    List<PaymentDTO> getAllPayments();
}
