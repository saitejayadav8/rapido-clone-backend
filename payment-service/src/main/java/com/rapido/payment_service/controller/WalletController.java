package com.rapido.payment_service.controller;

import com.rapido.payment_service.entity.Wallet;
import com.rapido.payment_service.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Wallet Service Working");
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Wallet> createWallet(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(
                walletService.createWallet(userId)
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Wallet> getWallet(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(
                walletService.getWallet(userId)
        );
    }

    @PutMapping("/add/{userId}")
    public ResponseEntity<Wallet> addMoney(
            @PathVariable Long userId,
            @RequestParam Double amount
    ) {
        return ResponseEntity.ok(
                walletService.addMoney(userId, amount)
        );
    }

    @PutMapping("/deduct/{userId}")
    public ResponseEntity<Wallet> deductMoney(
            @PathVariable Long userId,
            @RequestParam Double amount
    ) {
        return ResponseEntity.ok(
                walletService.deductMoney(userId, amount)
        );
    }
}