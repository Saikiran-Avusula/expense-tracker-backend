package com.expense_tracker.controler;

import com.expense_tracker.dto.CategoryResponse;
import com.expense_tracker.model.Categories;
import com.expense_tracker.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Categories createCategory(@AuthenticationPrincipal  UserDetails principal,
                                     @RequestBody Categories category){
        return categoryService.createCategory(principal.getUsername(), category);
    }

    @GetMapping
    public List<CategoryResponse> list(@AuthenticationPrincipal UserDetails principal) {
        return categoryService.getCategories(principal.getUsername())
                .stream()
                .map(CategoryResponse::new)
                .toList();
    }


    @PutMapping("/{id}")
    public Categories categoryUpdate(@PathVariable Long id, @RequestBody Categories category){
        return categoryService.updateCategory(id, category);
    }

//    @DeleteMapping("/{id}")
//    public String categoryDelete(@PathVariable Long id){
//        categoryService.deleteCategory(id); // no returning statement for deleting
//        return "Category Id:" + id + " deleted successful";
//    }

//    instead of above we can return with 204 code :
@DeleteMapping("/{id}")
public ResponseEntity<Void> categoryDelete(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.noContent().build(); // returns 204 No Content
}

}
