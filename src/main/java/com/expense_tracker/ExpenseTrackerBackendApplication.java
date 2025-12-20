package com.expense_tracker;

import com.expense_tracker.model.Categories;
import com.expense_tracker.model.Expenses;
import com.expense_tracker.model.Users;
import com.expense_tracker.repositrory.CategoriesRepository;
import com.expense_tracker.repositrory.ExpensesRepository;
import com.expense_tracker.repositrory.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class ExpenseTrackerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerBackendApplication.class, args);

		System.out.println();
		System.out.println("Running successfully");
	}


//	testing with dummy data for CRUD operations
//	@Bean
//	CommandLineRunner commandLineRunner(UsersRepository usersRepository,
//										CategoriesRepository categoriesRepository,
//										ExpensesRepository expensesRepository){
//		return args -> {
//
////			creating a dummy user
//			Users user = new Users();
//			user.setName("testUser");
//			user.setEmail("testuser@gmail.com");
//			user.setPassword("testUser@123");
//			user.setCreatedAt(LocalDateTime.now());
//			usersRepository.save(user);
//
////			creating a dummy category of user expenses
//			Categories category = new Categories();
//			category.setUser(user);
//			category.setColor("green");
//			category.setName("Food");
//			category.setMonthlyBudget(new BigDecimal("1000.00"));
//			category.setCreatedAt(LocalDateTime.now());
//			categoriesRepository.save(category);
//
//
////			creating a dummy expenses of user
//			Expenses expenses = new Expenses();
//			expenses.setUser(user);
//			expenses.setCategory(category);
//			expenses.setAmount(new BigDecimal("500.00"));
//			expenses.setDescription("for monthly uses");
//			expenses.setExpenseDate(LocalDate.now());
//			expensesRepository.save(expenses);
//
//
//
////			4. print in console
//			System.out.println("All dummy data inserted successfully");
//			System.out.println("Users : "+ usersRepository.findAll());
//			System.out.println("category: "+ categoriesRepository.findAll());
//			System.out.println("expenses: "+expensesRepository.findAll());
//
//
//		};
//	}



//		deleting the user which created
//	@Bean
//	CommandLineRunner run(UsersRepository usersRepo) {
//		return args -> {
//
//			// Create a dummy user
//			Users user = new Users();
//			user.setEmail("delete.me@example.com");
//			user.setPassword("password123");
//			user.setName("Delete Me");
//			usersRepo.save(user);
//
//			System.out.println("User created: " + user);
//
//			// Delete by ID
//			usersRepo.deleteById(user.getId());
//			System.out.println("User deleted by ID: " + user.getId());
//
//			// OR delete by object
//			// usersRepo.delete(user);
//
//			// Verify deletion
//			System.out.println("Remaining users: " + usersRepo.findAll());
//		};
//	}

}
