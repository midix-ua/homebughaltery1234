package com.example.demo.repo;

import com.example.demo.model.Account;
import com.example.demo.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditRepo extends JpaRepository<Credit, Long> {
    boolean existsByAccount(Account account);
    boolean existsById(Long id);
    Optional<Credit> findById(Long id);
    Credit findByAccount(Account account);
    List<Credit> findByAccount_Login(String login);
}
