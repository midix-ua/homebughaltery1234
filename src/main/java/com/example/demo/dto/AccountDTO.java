package com.example.demo.dto;


import com.example.demo.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountDTO {
    private final String name;
    private final String email;
    private final String password;
    private final String surname;
    private final String phone;

    private final Set<Income> incomes = new HashSet<>();
    private final Set<Credit> credits= new HashSet<>();
    private final Set<Cumulation> cumulations= new HashSet<>();
    private final Set<Spending> spendings=new HashSet<>();
    private final TotalMoney totalMoney= new TotalMoney();
    private final String login ;

    public AccountDTO(String email, String name, String password, String surname, String phone,  String login) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.surname = surname;
        this.phone = phone;
        this.login = login;
    }

    public static AccountDTO of(String name, String email, String password, String surname, String phone, String login) {
        return new AccountDTO(name,email,password,surname,phone,login);
    }

    public Set<Credit> getCredits() {
        return credits;
    }

    public Set<Cumulation> getCumulations() {
        return cumulations;
    }

    public String getEmail() {
        return email;
    }

    public Set<Income> getIncomes() {
        return incomes;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public Set<Spending> getSpendings() {
        return spendings;
    }

    public String getSurname() {
        return surname;
    }

    public TotalMoney getTotalMoney() {
        return totalMoney;
    }
}