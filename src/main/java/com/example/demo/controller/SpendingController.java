package com.example.demo.controller;

import com.example.demo.dto.SpendingDTO;
import com.example.demo.model.Spending;
import com.example.demo.repo.AccountRepo;
import com.example.demo.service.SpendingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class SpendingController {

    private final SpendingService spendingService;
    private final AccountRepo accountRepo;

    public SpendingController(SpendingService spendingService, AccountRepo accountRepo) {
        this.spendingService = spendingService;
        this.accountRepo = accountRepo;
    }

    @GetMapping("/addSpending")
    public String addSpending() {
        return "addSpending";
    }

    @GetMapping("/spendingList")
    public String spendingList(Model model, Principal principal) {
        String login = principal.getName();
        List<Spending> spendings = spendingService.getSpendingRepo().findByAccount_Login(login);

        // Поточна дата
        LocalDate now = LocalDate.now();

        // Фільтруємо витрати лише за поточний місяць і рік
        List<Spending> currentMonthSpendings = spendings.stream()
                .filter(s -> s.getDate().getMonthValue() == now.getMonthValue()
                        && s.getDate().getYear() == now.getYear())
                .collect(Collectors.toList());

        // Групуємо по категоріях
        Map<String, Double> categoryTotals = currentMonthSpendings.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getCategory().toString(),
                        Collectors.summingDouble(Spending::getMonthly_spending)
                ));
        model.addAttribute("categoryTotals", categoryTotals);
        model.addAttribute("spendings", spendings);
        return "spendingList";
    }

    @PostMapping("/addSpending")
    public String addSpending(@ModelAttribute SpendingDTO spendingDTO, Principal principal) {
        spendingService.addSpending(spendingDTO, spendingService.getSpendingRepo(), principal.getName(), accountRepo);
        return "redirect:/spendingList";
    }

    @PostMapping("/spendingList/delete/{id}")
    public String deleteSpending(@PathVariable("id") Long id, Principal principal) {
        String login = principal.getName();
        spendingService.deleteSpending(login, spendingService.getSpendingRepo(), id);
        return "redirect:/spendingList";
    }
}