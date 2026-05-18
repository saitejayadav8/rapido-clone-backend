package com.rapido.payment_service.controller;

import com.rapido.payment_service.dto.PaymentRequest;
import com.rapido.payment_service.dto.RefundRequest;
import com.rapido.payment_service.dto.TopUpRequest;
import com.rapido.payment_service.entity.Transaction;
import com.rapido.payment_service.entity.Wallet;
import com.rapido.payment_service.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/wallet/create/{userId}")
    public ResponseEntity<Wallet> createWallet(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                paymentService.createWallet(userId)
        );
    }

    @PostMapping("/wallet/topup")
    public ResponseEntity<Wallet> topUpWallet(
            @RequestBody TopUpRequest request) {

        return ResponseEntity.ok(
                paymentService.topUpWallet(
                        request.getUserId(),
                        request.getAmount()
                )
        );
    }

    @GetMapping("/wallet/balance/{userId}")
    public ResponseEntity<Double> getBalance(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                paymentService.getBalance(userId)
        );
    }

    @PostMapping("/payment/pay")
    public ResponseEntity<Transaction> payForRide(
            @RequestBody PaymentRequest request) {

        return ResponseEntity.ok(
                paymentService.processRidePayment(request)
        );
    }

    @PostMapping("/payment/refund")
    public ResponseEntity<String> refundRide(
            @RequestBody RefundRequest request) {

        return ResponseEntity.ok(
                paymentService.refundRide(request.getRideId())
        );
    }

    @GetMapping("/transactions/history/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionHistory(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                paymentService.getTransactionHistory(userId)
        );
    }
}