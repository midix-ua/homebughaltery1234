package com.example.demo.service;


import com.example.demo.dto.IncomeDTO;
import com.example.demo.model.Account;
import com.example.demo.model.Income;
import com.example.demo.repo.AccountRepo;
import com.example.demo.repo.IncomeRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {
    private final IncomeRepo incomeRepo;

    public IncomeService(IncomeRepo incomeRepo) {
        this.incomeRepo = incomeRepo;
    }

    public List<Income> getIncomesByAccountLogin(String login) {
        return incomeRepo.findByAccount_Login(login);
    }

    public IncomeRepo getIncomeRepo() {
        return incomeRepo;
    }

    @Transactional
    public void addIncome(IncomeDTO incomeDTO, String login, IncomeRepo incomeRepo, AccountRepo accountRepo) {
        Account account = accountRepo.findByLogin(login);
        if (account != null) {
            Income income = new Income();
            income.setAccount(account);
            income.setMonthly_income(incomeDTO.getMonthly_income());
            income.setDate(LocalDate.now());
            incomeRepo.save(income);
        } else {
            throw new IllegalArgumentException("Account with login " + login + " not found");
        }
    }

    @Transactional
    public void deleteIncome(  Long id,IncomeRepo repo ,String login) {
        Optional<Income> incomeOpt = repo.findById(id);

        if (incomeOpt.isEmpty()) {
            throw new IllegalArgumentException("Income with id " + id + " not found");
        }

        Income income = incomeOpt.get();

        if (!income.getAccount().getLogin().equals(login)) {
            throw new IllegalArgumentException("Income does not belong to user");
        }

        repo.delete(income);
    }


}
