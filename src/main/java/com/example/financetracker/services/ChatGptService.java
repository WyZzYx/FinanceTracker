package com.example.financetracker.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatGptService {

    @Value("${openai.api.key}")
    private String apiKey;

    public String generateSummary(double income, double expenses, double net) {
        String prompt = String.format(
                "Provide a short summary of a user's finances: Income is $%.2f, Expenses are $%.2f, Net balance is $%.2f.",
                income, expenses, net
        );

        // Call OpenAI or any other backend here.
        // Example: use WebClient or OkHttp to call https://api.openai.com/v1/chat/completions

        return "Sample summary for now: Income $%.2f, Expenses $%.2f, Net $%.2f.".formatted(income, expenses, net);
    }
}
