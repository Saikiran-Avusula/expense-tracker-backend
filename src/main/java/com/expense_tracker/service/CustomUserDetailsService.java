package com.expense_tracker.service;

import com.expense_tracker.model.Users;
import com.expense_tracker.repositrory.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */

    public final UsersRepository usersRepository;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(userEmail);

        if(user == null){
            throw new RuntimeException("User not found: "+user);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER") // adjust role/authorities as needed
                .build();
    }
}
