package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="spending")
@EntityListeners(Spending.SpendingListener.class)
public class Spending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @Enumerated(EnumType.STRING)
    private SpendingCategory category;
    private Integer monthly_spending;
    private LocalDate date;

    public Spending(Account account) {
        this.account = account;
    }

    public Spending() {

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

    public Integer getMonthly_spending() {
        return monthly_spending;
    }

    public void setMonthly_spending(Integer monthly_spending) {
        this.monthly_spending = monthly_spending;
    }

    public SpendingCategory getCategory() {
        return category;
    }

    public void setCategory(SpendingCategory category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Spending spending = (Spending) o;
        return Objects.equals(id, spending.id) && Objects.equals(account, spending.account) && category == spending.category && Objects.equals(monthly_spending, spending.monthly_spending) && Objects.equals(date, spending.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, category, monthly_spending, date);
    }

    public static class SpendingListener {
        @PrePersist
        public void prePersist(Spending a) {
            System.out.println("[Callback] About to persist: " + a.getId());
        }

        @PostLoad
        public void postLoad(Spending a ) {
            System.out.println("[Callback] Loaded from DB: " + a);
        }
    }
}
