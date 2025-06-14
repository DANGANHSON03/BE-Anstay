package com.anstay.controller;

import com.anstay.dto.CreatePaymentRequest;
import com.anstay.dto.PaymentDTO;
import com.anstay.entity.Payment;
import com.anstay.repository.PaymentRepository;
import com.anstay.service.PaymentService;
import com.anstay.enums.PaymentStatus;
import com.anstay.util.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "https://anstay.com.vn") // Sửa nếu FE chạy port khác
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentRepository paymentRepository;

    // 1. Tạo thanh toán qua Momo (chuẩn cho FE gọi khi đặt phòng)
    @PostMapping("/momo")
    public ResponseEntity<?> createMomoPayment(@RequestBody CreatePaymentRequest request) {
        String payUrl = paymentService.createMomoPayment(request);
        return ResponseEntity.ok(Collections.singletonMap("payUrl", payUrl));
    }

    // 2. Nhận IPN từ Momo (notify sau khi khách thanh toán xong)
    @PostMapping("/momo/ipn")
    public ResponseEntity<?> momoIpnNotify(@RequestBody Map<String, Object> notifyData) {
        paymentService.handleMomoIpn(notifyData);
        return ResponseEntity.ok("OK");
    }

    // 3. Lấy chi tiết 1 payment
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Integer id) {
        PaymentDTO paymentDTO = paymentService.getPaymentById(id);
        return ResponseEntity.ok(paymentDTO);
    }

    // 4. Lấy tất cả payment (cho admin)
    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    // 5. Lấy lịch sử thanh toán của 1 user (tùy nhu cầu)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUser(@PathVariable Integer userId) {
        List<PaymentDTO> list = paymentService.getPaymentsByUserId(userId);
        return ResponseEntity.ok(list);
    }

    // 6. Lấy theo trạng thái (completed, failed, ...)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByStatus(@PathVariable PaymentStatus status) {
        List<PaymentDTO> list = paymentService.getPaymentsByStatus(status);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<PaymentDTO> getByTransactionId(@PathVariable String transactionId) {
        Payment payment = paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy giao dịch!"));
        return ResponseEntity.ok(PaymentMapper.toDTO(payment));
    }

    @PostMapping("/cash")
    public ResponseEntity<PaymentDTO> createCashPayment(@RequestBody CreatePaymentRequest request) {
        PaymentDTO paymentDTO = paymentService.createCashPayment(request);
        return ResponseEntity.ok(paymentDTO);
    }
}
