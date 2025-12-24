package com.expense_tracker.service;

import com.expense_tracker.model.Users;
import com.expense_tracker.repositrory.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UsersRepository usersRepo;
    private final PasswordEncoder encoder;

    public UserService(UsersRepository usersRepo, PasswordEncoder encoder) {
        this.usersRepo = usersRepo;
        this.encoder = encoder;
    }

    public Users register(String email, String rawPassword, String name) {
        if (usersRepo.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email already registered");
        }
        Users u = new Users();
        u.setEmail(email);
        u.setPassword(encoder.encode(rawPassword));
        u.setName(name);
        return usersRepo.save(u);
    }

    public Users findByEmail(String email) {
        return usersRepo.findByEmail(email);
    }

    public void updatePassword(String email, String newPassword) {
        Users user = usersRepo.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
        user.setPassword(encoder.encode(newPassword));
        usersRepo.save(user);
    }
}
