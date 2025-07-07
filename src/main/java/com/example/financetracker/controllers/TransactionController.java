package com.example.financetracker.controllers;

import com.example.financetracker.models.Transaction;
import com.example.financetracker.DTO.TransactionDTO;
import com.example.financetracker.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/{username}")
    public ResponseEntity<Transaction> addTransaction(@PathVariable String username,
                                                      @RequestBody TransactionDTO dto) {
        Transaction saved = transactionService.addTransaction(username, dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Transaction>> getUserTransactions(@PathVariable String username) {
        List<Transaction> transactions = transactionService.getTransactionsByUsername(username);
        return ResponseEntity.ok(transactions);
    }
}
