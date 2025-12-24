package com.expense_tracker.dto;

import com.expense_tracker.model.Expenses;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExpenseResponse {
    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDate expenseDate;
    private LocalDateTime createdAt;
    private com.expense_tracker.model.Categories category; // Return full object
    private String userEmail;

    public ExpenseResponse(Expenses expense) {
        this.id = expense.getId();
        this.amount = expense.getAmount();
        this.description = expense.getDescription();
        this.expenseDate = expense.getExpenseDate();
        this.createdAt = expense.getCreatedAt();
        this.category = expense.getCategory(); // Set full object
        this.userEmail = expense.getUser() != null ? expense.getUser().getEmail() : null;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public com.expense_tracker.model.Categories getCategory() {
        return category;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
