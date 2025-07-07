package com.example.financetracker.services;

import com.example.financetracker.models.Account;
import com.example.financetracker.models.ApplicationUser;
import com.example.financetracker.repository.AccountRepository;
import com.example.financetracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public Account getAccountByUsername(String username) {
        Optional<ApplicationUser> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return null;
        }

        Optional<Account> account = accountRepository.findByUser(user.get());
        return account.orElse(null);
    }
}
