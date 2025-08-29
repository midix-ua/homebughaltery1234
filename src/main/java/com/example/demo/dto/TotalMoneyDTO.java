package com.example.demo.dto;


import com.example.demo.model.Account;

public class TotalMoneyDTO {
    private final Account account;
    private final Integer summ;

    public TotalMoneyDTO(Account account, Integer summ) {
        this.account = account;
        this.summ = summ;
    }

    public static TotalMoneyDTO of(Account account, Integer summ) {
        return new TotalMoneyDTO(account, summ);
    }

    public Account getAccount() {
        return account;
    }

    public Integer getSumm() {
        return summ;
    }

}
