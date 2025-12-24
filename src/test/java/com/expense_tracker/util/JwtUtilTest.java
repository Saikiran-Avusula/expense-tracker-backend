package com.expense_tracker.util;

import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtUtilTest {

    // Using the same key as in application.properties for reproduction
    private final String secret = "mysuperlongsecuresecretmysuperlongsecuresecretmysuperlongsecuresecretmysuperlongsecuresecret";
    private final long expiration = 1000 * 60 * 60; // 1 hour

    private final JwtUtil jwtUtil = new JwtUtil(secret, expiration);

    @Test
    public void testTokenGenerationAndValidation() {
        String username = "testuser";
        String token = jwtUtil.generateToken(username);

        Assertions.assertNotNull(token);
        Assertions.assertTrue(jwtUtil.isTokenValid(token, username));
        Assertions.assertEquals(username, jwtUtil.extractUsername(token));
    }

    @Test
    public void testInvalidSignature() {
        String username = "testuser";
        String token = jwtUtil.generateToken(username);
        
        // Tamper with the token (e.g., change the last character)
        String tamperedToken = token.substring(0, token.length() - 1) + "X";

        // Since we wrapped the call in try-catch in the filter, here we expect the utility itself
        // to throw an exception OR return false if we handled it there.
        // Checking JwtUtil.isTokenValid implementation:
        // it catches JwtException and returns false.
        
        Assertions.assertFalse(jwtUtil.isTokenValid(tamperedToken, username));
    }
    
    @Test
    public void testWrongUsername() {
        String username = "testuser";
        String token = jwtUtil.generateToken(username);
        
        Assertions.assertFalse(jwtUtil.isTokenValid(token, "otheruser"));
    }
}
