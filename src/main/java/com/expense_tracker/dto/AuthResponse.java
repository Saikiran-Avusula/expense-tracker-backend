package com.expense_tracker.dto;

public class AuthResponse {

    private String token;
    private String userName;
    private String userEmail;

    public AuthResponse(String token, String userName, String userEmail) {
        this.token = token;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public String getToken() {
        return token;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
