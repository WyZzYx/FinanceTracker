package com.example.financetracker.DTO;

public class AccountResponseDTO {
    private String name;
    private double balance;

    public AccountResponseDTO(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
}
