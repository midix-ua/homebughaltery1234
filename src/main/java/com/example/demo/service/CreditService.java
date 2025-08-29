package com.example.demo.service;

import com.example.demo.dto.CreditDTO;
import com.example.demo.model.Account;
import com.example.demo.model.Credit;
import com.example.demo.repo.AccountRepo;
import com.example.demo.repo.CreditRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CreditService {
    private final CreditRepo creditRepo;

    public CreditService(CreditRepo creditRepo) {
        this.creditRepo = creditRepo;
    }

    public List<Credit> getCreditsByAccountLogin(String login) {
        return creditRepo.findByAccount_Login(login);
    }

    public CreditRepo getCreditRepo() {
        return creditRepo;
    }

    @Transactional
    public void addCredit(CreditDTO creditDTO, CreditRepo creditRepo,String login, AccountRepo accountRepo) {
    Account account = accountRepo.findByLogin(login);
    if(account!=null){
        Credit credit = new Credit();
        credit.setAccount(account);
        credit.setThelastdate(creditDTO.getThelastdate());
        credit.setStatus(true);
        credit.setSummarry(creditDTO.getSummarry());
        creditRepo.save(credit);
    } else{
        throw new IllegalArgumentException("Account with login"+login+" not found");
    }

    }

    @Transactional
    public void toggleStatus(CreditRepo creditRepo, String login, Long id)  {
      List<Credit> credits = creditRepo.findByAccount_Login(login);
        for(Credit credit : credits){
            if (credit.getId().equals(id)) {
                credit.setStatus(!credit.getStatus());
                creditRepo.save(credit);
            }
        }

    }

    @Transactional
    public void deleteCredit(Long id, CreditRepo creditRepo, String login) {
       List<Credit> credits = creditRepo.findByAccount_Login(login);
       for(Credit credit : credits){
           if (credit.getId().equals(id)) {
               creditRepo.delete(credit);
           }
       }
    }

    @Transactional
    public void autoCloseExpiredCredits() {
        List<Credit> allCredits = creditRepo.findAll();
        LocalDate today = LocalDate.now();

        for (Credit credit : allCredits) {
            LocalDate endDate = credit.getThelastdate();
            Boolean isActive = credit.getStatus();

            if (Boolean.TRUE.equals(isActive) && endDate != null && !endDate.isAfter(today)) {
                credit.setStatus(false);
                creditRepo.save(credit);
            }
        }
    }
}
