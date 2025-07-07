package com.example.financetracker.controllers;

import com.example.financetracker.models.Account;
import com.example.financetracker.DTO.AccountResponseDTO;
import com.example.financetracker.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acc")
@CrossOrigin("*")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{username}") // Change ID to username
    public ResponseEntity<?> getAccountInfo(@PathVariable String username) {

        Account account = accountService.getAccountByUsername(username);

        if (account == null) {
            return ResponseEntity.status(404).body("Account not found");
        }
        return ResponseEntity.ok(new AccountResponseDTO(account.getName(), account.getBalance()));
    }
}

