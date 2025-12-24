package com.expense_tracker.jwtFilter;

import com.expense_tracker.service.CustomUserDetailsService;
import com.expense_tracker.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
/**
    A once-per-request jwtFilter that:
        Reads Authorization: Bearer <token>
        Validates token
        Sets authentication context if valid
 */

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        before filtering the user
        String authorizationHeader = request.getHeader("Authorization");
        String usertoken = null;
        String username = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            usertoken = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(usertoken);
            } catch (Exception e) {
                // Log the exception or handle it (e.g., token invalid/expired)
                // For now, we suppress it so the filter chain continues (user remains unauthenticated)
                System.out.println("JWT Token validation failed: " + e.getMessage());
            }
        }


        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtUtil.isTokenValid(usertoken, userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }




}
