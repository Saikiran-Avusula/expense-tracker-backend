package com.expense_tracker.service;

import com.expense_tracker.model.Categories;
import com.expense_tracker.model.Expenses;
import com.expense_tracker.model.Users;
import com.expense_tracker.repositrory.CategoriesRepository;
import com.expense_tracker.repositrory.ExpensesRepository;
import com.expense_tracker.repositrory.UsersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpensesRepository expensesRepository;
    private final UsersRepository usersRepository;
    private final CategoriesRepository categoriesRepository;


    public ExpenseService(ExpensesRepository expensesRepository, UsersRepository usersRepository, CategoriesRepository categoriesRepository) {
        this.expensesRepository = expensesRepository;
        this.usersRepository = usersRepository;
        this.categoriesRepository = categoriesRepository;
    }

    public Expenses createExpense(String email, Expenses expense, Long categoryId){
        Users user = usersRepository.findByEmail(email);
        Categories category = categoriesRepository.findById(categoryId).orElseThrow();

        expense.setUser(user);
        expense.setCategory(category);
        return expensesRepository.save(expense);
    }

    public List<Expenses> getExpenses(String email){
        Users user = usersRepository.findByEmail(email);
        return expensesRepository.findByUser(user);
    }

    public Expenses updateExpenses(Long id, Long categoryId, Expenses updateExpense){

        Expenses updateWithExistingExpenses = expensesRepository.findById(id).orElseThrow();
        Categories category = categoriesRepository.findById(categoryId).orElseThrow();

        updateWithExistingExpenses.setAmount(updateExpense.getAmount());
        updateWithExistingExpenses.setDescription(updateExpense.getDescription());
        updateWithExistingExpenses.setExpenseDate(updateExpense.getExpenseDate());
        updateWithExistingExpenses.setCategory(category);

        return expensesRepository.save(updateWithExistingExpenses);
    }


    public void deleteExpenses(Long id){
        expensesRepository.deleteById(id);
    }

    public List<Expenses> getExpensesByDate(String email, LocalDate date) {
        Users user = usersRepository.findByEmail(email);
        return expensesRepository.findByUserAndExpenseDateBetween(user, date, date);
    }

    public List<Expenses> getMonthlyExpenses(String email, int year, int month) {
        Users user = usersRepository.findByEmail(email); LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        return expensesRepository.findByUserAndExpenseDateBetween(user, start, end); }

}
