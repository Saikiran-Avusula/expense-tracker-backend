package com.expense_tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.PrePersist;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //each category belongs to user
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private Users user;

    private String name;

    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal monthlyBudget;

    private String color;


    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
//    use case onCreate():
//    🧠 Even simpler analogy
//    Imagine you’re filling a form online.
//
//    When you click Submit, the system automatically adds:
//
//            “Submitted on: 2025‑01‑17 14:30”
//
//    You didn’t type it. The system added it just before saving.
//
//  That’s exactly what @PrePersist does.
//  even we can comment the "createdAt", because we're invoking the method but we need to record the time in DB, we just left it.


    public Categories() {
    }

    public Categories(Long id, Users user, String name, BigDecimal monthlyBudget, String color, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.monthlyBudget = monthlyBudget;
        this.color = color;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(BigDecimal monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", monthlyBudget=" + monthlyBudget +
                ", color='" + color + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}


