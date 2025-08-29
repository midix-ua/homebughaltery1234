package com.example.demo.service;

import com.example.demo.dto.TotalMoneyDTO;
import com.example.demo.model.Account;
import com.example.demo.model.Income;
import com.example.demo.model.Spending;
import com.example.demo.model.TotalMoney;
import com.example.demo.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TotalMoneyService {

    private final TotalMoneyRepo totalMoneyRepo;
    private final SpendingRepo spendingRepo;
    private final IncomeRepo incomeRepo;
    private final AccountRepo accountRepo;
    private final CreditRepo creditRepo;
    private final CreditService creditService;

    public TotalMoneyService(
            TotalMoneyRepo totalMoneyRepo,
            SpendingRepo spendingRepo,
            IncomeRepo incomeRepo,
            AccountRepo accountRepo,
            CreditRepo creditRepo, // ← додано
            CreditService creditService) {
        this.totalMoneyRepo = totalMoneyRepo;
        this.spendingRepo = spendingRepo;
        this.incomeRepo = incomeRepo;
        this.accountRepo = accountRepo;
        this.creditRepo = creditRepo; // ← додано
        this.creditService = creditService;
    }

    public TotalMoneyRepo getTotalMoneyRepo() {
        return totalMoneyRepo;
    }


    @Transactional
    public void updateOrCreateTotalMoneyForUser(String login) {
        creditService.autoCloseExpiredCredits();

        Account account = accountRepo.findByLogin(login);
        if (account == null) throw new IllegalArgumentException("Account not found");

        Integer totalIncome = incomeRepo.findByAccount_Login(login)
                .stream()
                .map(Income::getMonthly_income)
                .filter(Objects::nonNull)
                .reduce(0, Integer::sum);

        Integer totalSpending = spendingRepo.findByAccount_Login(login)
                .stream()
                .map(Spending::getMonthly_spending)
                .filter(Objects::nonNull)
                .reduce(0, Integer::sum);

        // 💳 Додаємо суму кредитів
        Integer totalCredit = creditRepo.findByAccount_Login(login)
                .stream()
                .filter(credit -> Boolean.TRUE.equals(credit.getStatus()))
                .map(credit -> credit.getSummarry())
                .filter(Objects::nonNull)
                .reduce(0, Integer::sum);

        int totalSum = totalIncome - totalSpending + totalCredit;

        Optional<TotalMoney> optionalTotalMoney = totalMoneyRepo.findByAccount_Login(login);

        if (optionalTotalMoney.isPresent()) {
            TotalMoney totalMoney = optionalTotalMoney.get();
            totalMoney.setSumm(totalSum);
            totalMoneyRepo.save(totalMoney);
        } else {
            TotalMoney totalMoney = new TotalMoney();
            totalMoney.setAccount(account);
            totalMoney.setSumm(totalSum);
            totalMoneyRepo.save(totalMoney);
        }
    }



}
