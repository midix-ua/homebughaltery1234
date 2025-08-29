package com.example.demo.controller;


import com.example.demo.dto.IncomeDTO;
import com.example.demo.model.Account;
import com.example.demo.model.Income;
import com.example.demo.repo.AccountRepo;
import com.example.demo.service.IncomeService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Controller
public class IncomeController {

    private final IncomeService incomeService;
    private final AccountRepo accountRepo;

    public IncomeController(IncomeService incomeService, AccountRepo accountRepo) {
        this.incomeService = incomeService;
        this.accountRepo = accountRepo;
    }

    @GetMapping("/addIncome")
    public String addIncome() {
        return "addIncome";
    }

    @GetMapping("/incomeList")
    public String incomeList(Model model, Principal principal) {
        String login = principal.getName();
        List<Income> incomes = incomeService.getIncomeRepo().findByAccount_Login(login);
        model.addAttribute("incomes", incomes);
        return "incomeList";
    }

    @PostMapping("/addIncome")
    public String addIncome(@ModelAttribute IncomeDTO incomeDTO, Principal principal) {
        incomeService.addIncome(incomeDTO, principal.getName(), incomeService.getIncomeRepo(), accountRepo);
        return "redirect:/incomeList";
    }
@PostMapping("/incomeList/delete/{id}")
    public String deleteIncome(@PathVariable Long id, Principal principal) {
        String login = principal.getName();
        incomeService.deleteIncome(id,incomeService.getIncomeRepo(), login);
        return "redirect:/incomeList";
}

}
