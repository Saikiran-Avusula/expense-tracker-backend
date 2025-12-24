package com.expense_tracker.dto;

import com.expense_tracker.model.Categories;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class CategoryResponse {
    private Long id;
    private String name;
    private BigDecimal monthlyBudget;
    private String color;
    private LocalDateTime createdAt;
    private String userEmail; // optional

    public CategoryResponse(Categories category) {
        this.id = category.getId();
        this.name = category.getName();
        this.monthlyBudget = category.getMonthlyBudget();
        this.color = category.getColor();
        this.createdAt = category.getCreatedAt();
        this.userEmail = category.getUser().getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getMonthlyBudget() {
        return monthlyBudget;
    }

    public String getColor() {
        return color;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
