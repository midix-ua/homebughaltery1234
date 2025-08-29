package com.example.demo.repo;

import com.example.demo.model.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    @EntityGraph(attributePaths = {"incomes", "spendings", "credits", "cumulations", "totalMoney"})
    Account findByLogin(String login);
    boolean existsByLogin(String login);
}