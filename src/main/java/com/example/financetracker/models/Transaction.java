package com.example.financetracker.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(nullable = false)
    private Date date;

    public Transaction(Long id, Account account, Date date, double amount, String category) {
        this.id = id;
        this.account = account;
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    @Column(nullable = false)
    private String category;

    public Transaction(Long id, Account account, Date date, double amount) {
        this.id = id;
        this.account = account;
        this.date = date;
        this.amount = amount;
    }

    @Column(nullable = false)
    private double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Transaction() {

    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
