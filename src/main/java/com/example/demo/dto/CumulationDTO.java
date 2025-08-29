package com.example.demo.dto;

import com.example.demo.model.Account;

public class CumulationDTO {

    private final Boolean status;
    private final String description;
    private final Account account;
    private final Integer summ;


    public CumulationDTO(Boolean status, String description, Account account, Integer summ) {
        this.status = status;
        this.description = description;
        this.account = account;
        this.summ = summ;
    }

    public static CumulationDTO of(Boolean status, String description, Account account, Integer summ){
        return new CumulationDTO(status, description, account, summ);
    }

    public Account getAccount() {
        return account;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getStatus() {
        return status;
    }

    public Integer getSumm() {
        return summ;
    }
}
