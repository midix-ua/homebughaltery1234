package com.example.demo.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="totalmoney")
@EntityListeners(TotalMoney.TotalMoneyListener.class)
public class TotalMoney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer summ;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public TotalMoney(Account account) {
        this.account = account;
    }

    public TotalMoney() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSumm(Integer summ) {
        this.summ = summ;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TotalMoney that = (TotalMoney) o;
        return Objects.equals(id, that.id) && Objects.equals(summ, that.summ) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, summ, account);
    }

    public Integer getSumm() {
        return summ;
    }


    public static class TotalMoneyListener {
        @PostPersist
        public void postPersist(TotalMoney a) {
            System.out.println("[Callback] Persisted TotalMoney with id = " + a.getId());
        }


        @PostLoad
        public void postLoad(TotalMoney a ) {
            System.out.println("[Callback] Loaded from DB: " + a);
        }
    }
}
