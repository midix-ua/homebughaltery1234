package com.example.demo.utils;

import com.example.demo.model.Account;
import com.example.demo.repo.AccountRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AccountRepo accountRepo;

    public UserDetailsService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountRepo.findByLogin(login);
        if (account == null) {
            throw new UsernameNotFoundException("Account not found: " + login);
        }
        return User.builder()
                .username(account.getLogin())
                .password(account.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
                .build();
    }
}