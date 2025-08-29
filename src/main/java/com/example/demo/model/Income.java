package com.example.demo.model;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="income")
@EntityListeners(Income.IncomeListener.class)
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private Integer monthly_income;
    private LocalDate date;

    public Income(Account account) {
        this.account = account;
    }

    public Income() {
    }



    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMonthly_income() {
        return monthly_income;
    }

    public void setMonthly_income(Integer monthly_income) {
        this.monthly_income = monthly_income;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Income income = (Income) o;
        return Objects.equals(id, income.id) && Objects.equals(account, income.account) && Objects.equals(monthly_income, income.monthly_income) && Objects.equals(date, income.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, monthly_income, date);
    }

    public static class IncomeListener {
        @PrePersist
        public void prePersist(Income a) {
            System.out.println("[Callback] About to persist: " + a.getId());
        }

        @PostLoad
        public void postLoad(Income a ) {
            System.out.println("[Callback] Loaded from DB: " + a);
        }
    }
}
