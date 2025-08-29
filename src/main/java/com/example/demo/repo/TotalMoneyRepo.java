package com.example.demo.repo;

import com.example.demo.model.Account;
import com.example.demo.model.TotalMoney;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TotalMoneyRepo extends JpaRepository<TotalMoney, Long> {
    boolean existsById(Long id);
    boolean existsByAccount(Account account);
    TotalMoney findByAccount(Account account);



    Optional<TotalMoney>  findByAccount_Login(String login);
    Optional<TotalMoney> findById(Long id);
}
