package com.example.demo.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="account")
@EntityListeners(Account.AccountListener.class)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String surname;
    private String phone;
    private String login;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Income> incomes = new HashSet<>();
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Spending> spendings =  new HashSet<>();
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private TotalMoney totalMoney = new  TotalMoney();
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Credit> credits = new HashSet<>();
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cumulation> cumulations = new HashSet<>();
    public Account() {
    }

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Set<Credit> getCredits() {
        return credits;
    }

    public void setCredits(Set<Credit> credits) {
        this.credits = credits;
    }

    public Set<Cumulation> getCumulations() {
        return cumulations;
    }

    public void setCumulations(Set<Cumulation> cumulations) {
        this.cumulations = cumulations;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Income> incomes) {
        this.incomes = incomes;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Spending> getSpendings() {
        return spendings;
    }

    public void setSpendings(Set<Spending> spendings) {
        this.spendings = spendings;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(name, account.name) && Objects.equals(email, account.email) && Objects.equals(password, account.password) && Objects.equals(surname, account.surname) && Objects.equals(phone, account.phone) && Objects.equals(login, account.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, surname, phone, login);
    }

    public TotalMoney getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(TotalMoney totalMoney) {
        this.totalMoney = totalMoney;
    }

    public static class AccountListener {
        @PrePersist
        public void prePersist(Account a) {
            System.out.println("[Callback] About to persist: " + a.getId());
        }

        @PostLoad
        public void postLoad(Account a ) {
            System.out.println("[Callback] Loaded from DB: " + a);
        }
    }
}
