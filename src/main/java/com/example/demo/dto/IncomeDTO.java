package com.example.demo.dto;


import com.example.demo.model.Account;

import java.time.LocalDate;
import java.util.Date;

public class IncomeDTO {
    private final Account account;
    private final Integer monthly_income;
    private final LocalDate date;


    public IncomeDTO(Account account, Integer monthly_income,LocalDate date) {
        this.account = account;
        this.monthly_income = monthly_income;
        this.date = date;
    }
    public static IncomeDTO of(Account account, Integer monthly_income, LocalDate date) {
        return new IncomeDTO(account, monthly_income, date);
    }

    public LocalDate getDate() {
        return date;
    }

    public Account getAccount() {
        return account;
    }

    public Integer getMonthly_income() {
        return monthly_income;
    }

}
