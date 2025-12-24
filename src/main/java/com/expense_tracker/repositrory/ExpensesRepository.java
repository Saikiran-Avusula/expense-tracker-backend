package com.expense_tracker.repositrory;


import com.expense_tracker.model.Categories;
import com.expense_tracker.model.Expenses;
import com.expense_tracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

//    all expenses of given user
    List<Expenses> findByUser(Users user);

//    all expenses of a given category
    List<Expenses> findByCategory(Categories category);

//    all expenses on specific date
    List<Expenses> findByExpenseDate(LocalDateTime createdAt);

    // Expenses between two dates (useful for reports)
    List<Expenses> findByExpenseDateBetween(LocalDate start, LocalDate end);

    // Expenses for a user in a given month
    List<Expenses> findByUserAndExpenseDateBetween(Users user, LocalDate start, LocalDate end);
}
