package com.example.demo.repo;

import com.example.demo.model.Account;
import com.example.demo.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {
    boolean existsByAccount(Account account);
    boolean existsById(Long id);
    Optional<Income> findById(Long id);
    Income findByAccount(Account account);
    List<Income> findByAccount_Login(String login);
}

