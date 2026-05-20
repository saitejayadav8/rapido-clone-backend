package com.rapido.payment_service.service;

import com.rapido.payment_service.entity.Wallet;
import com.rapido.payment_service.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    // Create Wallet
    public Wallet createWallet(Long userId) {

        Wallet wallet = new Wallet();

        wallet.setUserId(userId);
        wallet.setBalance(0.0);

        return walletRepository.save(wallet);
    }

    // Get Wallet
    public Wallet getWallet(Long userId) {

        return walletRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Wallet not found"));
    }

    // Add Money
    public Wallet addMoney(Long userId,
                           Double amount) {

        Wallet wallet = getWallet(userId);

        wallet.setBalance(
                wallet.getBalance() + amount
        );

        return walletRepository.save(wallet);
    }

    // Deduct Money
    public Wallet deductMoney(Long userId,
                              Double amount) {

        Wallet wallet = getWallet(userId);

        if (wallet.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        wallet.setBalance(
                wallet.getBalance() - amount
        );

        return walletRepository.save(wallet);
    }
}