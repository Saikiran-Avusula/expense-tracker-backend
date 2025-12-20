package com.expense_tracker.repositrory;


import com.expense_tracker.model.Categories;
import com.expense_tracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

//    finding the user based on category wise
    List<Categories> findByUser(Users user);
}
