package com.expense_tracker.repositrory;


import com.expense_tracker.model.Categories;
import com.expense_tracker.model.Expenses;
import com.expense_tracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

    List<Expenses> findByUser(Users user);
    List<Expenses> findByCategory(Categories category);
    List<Expenses> findByExpenseDate(LocalDateTime createdAt);
}
