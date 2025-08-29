package com.example.demo.controller;

import com.example.demo.dto.AccountDTO;
import com.example.demo.model.Spending;
import com.example.demo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.time.Year;
import java.util.*;

@Controller
public class AccountController {

    private final AccountService accountService;
    private final SpendingService spendingService;

    public AccountController(AccountService accountService, SpendingService spendingService) {
        this.accountService = accountService;
        this.spendingService = spendingService;
    }


    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @GetMapping("/login")
    public String  showLoginForm() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/account-details";
    }




    @GetMapping("/account-details")
    public String showAccountDetails(Model model, Principal principal) {
        String login = principal.getName();
        var account = accountService.getRepo().findByLogin(login);
        if (account == null) return "error";

        account.getIncomes().size();
        account.getSpendings().size();
        account.getCredits().size();
        account.getCumulations().size();
        account.getTotalMoney();
        model.addAttribute("account", account);
        return "account-details";
    }

    @PostMapping("/register")
    public String registerAccount(@RequestParam String login, @RequestParam String password, @ModelAttribute AccountDTO accountDTO) {
        accountService.addAccount(login, password, accountDTO);
        return "redirect:/login";
    }


}
