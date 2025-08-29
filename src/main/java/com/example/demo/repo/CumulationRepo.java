package com.example.demo.repo;

import com.example.demo.model.Account;
import com.example.demo.model.Cumulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface CumulationRepo extends JpaRepository<Cumulation,Long> {
    boolean existsByAccount(Account account);
    boolean existsById(Long id);
    Optional<Cumulation> findById(Long id);
    Cumulation findByAccount(Account account);
    List<Cumulation> findByAccount_Login(String login);

}
