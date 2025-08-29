package com.example.demo.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="credit")
@EntityListeners(Credit.CreditListener.class)
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private Boolean status;
    private Integer summarry;
    private String description;
    private LocalDate thelastdate;


    public Credit(Account account) {
        this.account = account;
    }

    public Credit() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return Objects.equals(id, credit.id) && Objects.equals(account, credit.account) && Objects.equals(status, credit.status) && Objects.equals(summarry, credit.summarry) && Objects.equals(description, credit.description) && Objects.equals(thelastdate, credit.thelastdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, status, summarry, description, thelastdate);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSummarry() {
        return summarry;
    }

    public void setSummarry(Integer summarry) {
        this.summarry = summarry;
    }

    public LocalDate getThelastdate() {
        return thelastdate;
    }

    public void setThelastdate(LocalDate thelastdate) {
        this.thelastdate = thelastdate;
    }

    public static class CreditListener {
        @PrePersist
        public void prePersist(Credit a) {
            System.out.println("[Callback] About to persist: " + a.getId());
        }

        @PostLoad
        public void postLoad(Credit a ) {
            System.out.println("[Callback] Loaded from DB: " + a);
        }
    }
}
