package com.example.demo.controller;

import com.example.demo.dto.CreditDTO;
import com.example.demo.model.Credit;
import com.example.demo.repo.AccountRepo;
import com.example.demo.service.CreditService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Controller
public class CreditController {

    private final CreditService creditService;
    private final AccountRepo accountRepo;

    public CreditController(CreditService creditService, AccountRepo accountRepo) {
        this.creditService = creditService;
        this.accountRepo = accountRepo;
    }

    @GetMapping("/getCredit")
    public String showCreditForm(Model model, CsrfToken token) {
        model.addAttribute("_csrf", token);
        return "getCredit";
    }


    @GetMapping("/creditList")
    public String showCreditList(Model model, Principal principal, CsrfToken token){
        creditService.autoCloseExpiredCredits();
        model.addAttribute("credits", creditService.getCreditRepo().findByAccount_Login(principal.getName()));
        model.addAttribute("_csrf", token);
        return "creditList";
    }

    @PostMapping("/getCredit")
    public String registerCredit(@ModelAttribute CreditDTO creditDTO, Principal principal) {
        creditService.addCredit(creditDTO, creditService.getCreditRepo(), principal.getName(), accountRepo);
        return "redirect:/creditList";
    }

    @PostMapping("/creditList/{id}")
    public String toggleCreditStatus(@PathVariable Long id, Principal principal) {
        String login = principal.getName();
        creditService.toggleStatus(creditService.getCreditRepo(), login, id);
        return "redirect:/creditList";
    }

    @PostMapping("/creditList/delete/{id}")
    public String deleteCredit(@PathVariable Long id, Principal principal) {
        String login = principal.getName();
        creditService.deleteCredit(id, creditService.getCreditRepo(), login);
        return "redirect:/creditList";
    }

}
