package com.expense_tracker.controler;

import com.expense_tracker.dto.AuthResponse;
import com.expense_tracker.dto.LoginRequest;
import com.expense_tracker.dto.RegisterRequest;
import com.expense_tracker.dto.ResetPasswordRequest;
import com.expense_tracker.model.Users;
import com.expense_tracker.service.UserService;
import com.expense_tracker.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, UserService userService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public Users register(@RequestBody RegisterRequest req) {
        return userService.register(req.getUserEmail(), req.getUserPassword(), req.getUserName());
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUserEmail(), req.getUserPassword()));
        
        String token = jwtUtil.generateToken(req.getUserEmail());
        Users user = userService.findByEmail(req.getUserEmail());
        
        return new AuthResponse(token, user.getName(), user.getEmail());
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody ResetPasswordRequest req) {
        userService.updatePassword(req.getUserEmail(), req.getNewPassword());
        return "Password reset successfully!";
    }
}
