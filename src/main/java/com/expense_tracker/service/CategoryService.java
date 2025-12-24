package com.expense_tracker.service;

import com.expense_tracker.model.Categories;
import com.expense_tracker.model.Users;
import com.expense_tracker.repositrory.CategoriesRepository;
import com.expense_tracker.repositrory.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoriesRepository categoriesRepository;
    private final UsersRepository usersRepository;

    public CategoryService(CategoriesRepository categoriesRepository, UsersRepository usersRepository) {
        this.categoriesRepository = categoriesRepository;
        this.usersRepository = usersRepository;
    }

    public Categories createCategory(String email, Categories category){
        Users user = usersRepository.findByEmail(email);
        category.setUser(user);
        return categoriesRepository.save(category);
    }

    public List<Categories> getCategories(String email){
        Users user = usersRepository.findByEmail(email);
        return categoriesRepository.findByUser(user);
    }

    public Categories updateCategory(Long userId, Categories updatedCategory){
        Categories existingCategory = categoriesRepository.findById(userId).orElseThrow();
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setMonthlyBudget(updatedCategory.getMonthlyBudget());
        existingCategory.setColor(updatedCategory.getColor());
        return categoriesRepository.save(existingCategory);
    }

    public void deleteCategory(Long userId){
        categoriesRepository.deleteById(userId);
    }
}
