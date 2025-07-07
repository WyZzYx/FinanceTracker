package com.example.financetracker.services;

import com.example.financetracker.models.Account;
import com.example.financetracker.models.ApplicationUser;
import com.example.financetracker.DTO.StatisticsDTO;
import com.example.financetracker.models.Transaction;
import com.example.financetracker.repository.AccountRepository;
import com.example.financetracker.repository.TransactionRepository;
import com.example.financetracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final ChatGptService chatGptService;

    public StatisticsService(UserRepository userRepository, AccountRepository accountRepository,
                             TransactionRepository transactionRepository, ChatGptService chatGptService) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.chatGptService = chatGptService;
    }

    public StatisticsDTO getStatisticsForUser(String username) {
        ApplicationUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Account account = accountRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        List<Transaction> transactions = transactionRepository.findAllByAccount(account);

        double income = 0, expense = 0;
        for (Transaction tx : transactions) {
            if (tx.getAmount() > 0) income += tx.getAmount();
            else expense += Math.abs(tx.getAmount());
        }

        String summary = chatGptService.generateSummary(income, expense, income - expense);
        return new StatisticsDTO(income, expense, summary);
    }

    public Map<String, StatisticsDTO> getStatisticsForAllUsers() {
        Map<String, StatisticsDTO> result = new HashMap<>();
        List<ApplicationUser> users = userRepository.findAll();

        for (ApplicationUser user : users) {
            result.put(user.getUsername(), getStatisticsForUser(user.getUsername()));
        }

        return result;
    }
}

