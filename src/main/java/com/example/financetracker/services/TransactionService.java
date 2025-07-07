package com.example.financetracker.services;

import com.example.financetracker.models.Account;
import com.example.financetracker.models.ApplicationUser;
import com.example.financetracker.models.Transaction;
import com.example.financetracker.DTO.TransactionDTO;
import com.example.financetracker.repository.AccountRepository;
import com.example.financetracker.repository.TransactionRepository;
import com.example.financetracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(UserRepository userRepository, AccountRepository accountRepository,
                              TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction addTransaction(String username, TransactionDTO dto) {
        ApplicationUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = accountRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Transaction tx = new Transaction();
        tx.setAccount(account);
        tx.setAmount(dto.getAmount());
        tx.setCategory(dto.getCategory());

        // ✅ Use the date from frontend or fallback to current
        tx.setDate(dto.getDate() != null ? dto.getDate() : new Date());

        transactionRepository.save(tx);

        // ✅ Update account balance
        account.setBalance(account.getBalance() + dto.getAmount());
        accountRepository.save(account);

        return tx;
    }
    public List<Transaction> getTransactionsByUsername(String username) {
        ApplicationUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = accountRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return transactionRepository.findAllByAccount(account);
    }

}
