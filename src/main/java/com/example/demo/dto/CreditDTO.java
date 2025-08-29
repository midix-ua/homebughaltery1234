package com.example.demo.dto;

import com.example.demo.model.Account;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDate;
import java.util.Date;

public class CreditDTO {

    private final Account account;
    private final String description;
    private final Integer summarry;
    private final Boolean status;
    private final LocalDate thelastdate;

    public CreditDTO(Account account, String description, Integer summarry, Boolean status,  LocalDate thelastdate) {
        this.account = account;
        this.description = description;
        this.summarry = summarry;
        this.status = status;
        this.thelastdate = thelastdate;
    }

    public static CreditDTO of(Account account, String description, Integer summarry, Boolean status,  LocalDate thelastdate) {
        return new CreditDTO(account, description, summarry, status, thelastdate);
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

    public Integer getSummarry() {
        return summarry;
    }

    public LocalDate getThelastdate() {
        return thelastdate;
    }
}
