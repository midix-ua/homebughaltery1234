package com.example.demo.service;

import com.example.demo.dto.AccountDTO;
import com.example.demo.model.Account;
import com.example.demo.model.TotalMoney;
import com.example.demo.repo.AccountRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepo accountRepo;
    private final PasswordEncoder passwordEncoder;
    private final TotalMoneyService totalMoneyService;

    public AccountService(AccountRepo accountRepo, PasswordEncoder passwordEncoder, TotalMoneyService totalMoneyService) {
        this.accountRepo = accountRepo;
        this.passwordEncoder = passwordEncoder;
        this.totalMoneyService = totalMoneyService;
    }

    public AccountRepo getRepo(){
        return accountRepo;
    }

    public List<Account> getAccounts() {
        return accountRepo.findAll();
    }

    @Transactional
    public void addAccount(String login, String password, AccountDTO accountDTO) {
        Account account = new Account();
        if(!accountRepo.existsByLogin(login)) {
            account.setName(accountDTO.getName());
            account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
            account.setEmail(accountDTO.getEmail());
            account.setPhone(accountDTO.getPhone());
            account.setLogin(accountDTO.getLogin());
            account.setSurname(accountDTO.getSurname());
            account.setCredits(new HashSet<>());
            account.setCumulations(new HashSet<>());
            account.setIncomes(new HashSet<>());
            account.setSpendings(new HashSet<>());

            TotalMoney totalMoney = new TotalMoney();
            totalMoney.setAccount(account);
            account.setTotalMoney(totalMoney);

            accountRepo.save(account);
        } else {
            throw new AccountAlreadyExists("Account with login"+login+ " already exists!!");
        }
    }



}
