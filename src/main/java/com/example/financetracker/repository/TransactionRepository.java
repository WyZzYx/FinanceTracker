package com.example.financetracker.repository;

import com.example.financetracker.models.Account;
import com.example.financetracker.models.ApplicationUser;
import com.example.financetracker.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount_User(ApplicationUser user);
    List<Transaction> findAllByAccount(Account account);


}
