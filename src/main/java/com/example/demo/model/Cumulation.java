package com.example.demo.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="cumulation")
@EntityListeners({Cumulation.CumulationListener.class})
public class Cumulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean status;
    private String description;
    private Integer summ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Cumulation(Account account) {
        this.account = account;
    }

    public Cumulation() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cumulation that = (Cumulation) o;
        return Objects.equals(id, that.id) && Objects.equals(status, that.status) && Objects.equals(description, that.description) && Objects.equals(summ, that.summ) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, description, summ, account);
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

    public Integer getSumm() {
        return summ;
    }

    public void setSumm(Integer summ) {
        this.summ = summ;
    }

    public static class CumulationListener {
        @PrePersist
        public void prePersist(Cumulation a) {
            System.out.println("[Callback] About to persist: " + a.getId());
        }

        @PostLoad
        public void postLoad(Cumulation a ) {
            System.out.println("[Callback] Loaded from DB: " + a);
        }
    }
}

