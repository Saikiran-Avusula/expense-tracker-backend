package com.expense_tracker.controler;

import com.expense_tracker.dto.ExpenseResponse;
import com.expense_tracker.model.Expenses;
import com.expense_tracker.service.ExpenseService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/{categoryId}")
    public Expenses create(@AuthenticationPrincipal UserDetails principal,
                          @RequestBody Expenses expense,
                          @PathVariable Long categoryId) {

        return expenseService.createExpense(principal.getUsername(), expense, categoryId);
    }

    @GetMapping
    public List<ExpenseResponse> list(@AuthenticationPrincipal UserDetails principal) {
        return expenseService.getExpenses(principal.getUsername())
                .stream()
                .map(ExpenseResponse::new)   // ✅ pass the entity into constructor
                .toList();
    }



    @PutMapping("/{id}/category/{categoryId}")
    public Expenses update(@PathVariable Long id, @PathVariable Long categoryId, @RequestBody Expenses expense) {
        return expenseService.updateExpenses(id, categoryId, expense);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        expenseService.deleteExpenses(id);
        return "Expense id Deleted: " + id;
    }

    @GetMapping("/date/{date}")
    public List<Expenses> byDate(@AuthenticationPrincipal UserDetails principal, @PathVariable String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return expenseService.getExpensesByDate(principal.getUsername(), parsedDate);
    }

    @GetMapping("/month/{year}/{month}")
    public List<Expenses> byMonth(@AuthenticationPrincipal UserDetails principal, @PathVariable int year, @PathVariable int month) {
        return expenseService.getMonthlyExpenses(principal.getUsername(), year, month);
    }

}
