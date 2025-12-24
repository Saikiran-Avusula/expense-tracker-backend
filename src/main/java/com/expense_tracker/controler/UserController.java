package com.expense_tracker.controler;

import com.expense_tracker.model.Users;
import com.expense_tracker.repositrory.UsersRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UsersRepository usersRepo;

    public UserController(UsersRepository usersRepo) {
        this.usersRepo = usersRepo;
    }

    @GetMapping("/me")
    public UserDetails me(@AuthenticationPrincipal UserDetails principal) {
        return principal;
    }

}
