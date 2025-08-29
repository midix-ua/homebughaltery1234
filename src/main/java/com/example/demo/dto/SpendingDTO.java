package com.example.demo.dto;


import com.example.demo.model.Account;
import com.example.demo.model.SpendingCategory;

import java.time.LocalDate;
import java.util.Date;

public class SpendingDTO {
    private final Account account;
    private final Integer monthly_spending;
    private final LocalDate date;
    private final SpendingCategory category;

    public SpendingDTO(Account account, Integer monthly_spending,LocalDate date, SpendingCategory category) {
        this.account = account;
        this.monthly_spending = monthly_spending;
       this.date = date;
       this.category = category;
    }

    public static SpendingDTO of(Account account,  Integer monthly_spending,LocalDate date, SpendingCategory category) {
        return new SpendingDTO(account, monthly_spending,date, category);
    }

    public Account getAccount() {
        return account;
    }

    public Integer getMonthly_Spending() {
        return monthly_spending;
    }

    public LocalDate getDate() {
        return date;
    }

    public SpendingCategory getCategory() {
        return category;
    }

}
