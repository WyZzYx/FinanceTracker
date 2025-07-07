package com.example.financetracker.models;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Ensure auto-increment
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private ApplicationUser user; // Correct reference

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double balance;

    public Account(ApplicationUser user, String name, double balance) {
        this.user = user;
        this.name = name;
        this.balance = balance;
    }

    public Account() {}

    public Long getId() {
        return id;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", user=" + (user != null ? user.getUsername() : "null") +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
