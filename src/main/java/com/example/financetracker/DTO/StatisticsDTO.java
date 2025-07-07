package com.example.financetracker.DTO;

public class StatisticsDTO {
    private double totalIncome;
    private double totalExpenses;
    private double netBalance;
    private String summaryText;

    public StatisticsDTO(double income, double expense, String summaryText) {
        this.totalIncome = income;
        this.totalExpenses = expense;
        this.netBalance = income - expense;
        this.summaryText = summaryText;
    }

    // Getters
    public double getTotalIncome() { return totalIncome; }
    public double getTotalExpenses() { return totalExpenses; }
    public double getNetBalance() { return netBalance; }
    public String getSummaryText() { return summaryText; }
}