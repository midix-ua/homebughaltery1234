package com.example.demo.service;

import com.example.demo.dto.CumulationDTO;
import com.example.demo.model.Account;
import com.example.demo.model.Cumulation;
import com.example.demo.repo.AccountRepo;
import com.example.demo.repo.CumulationRepo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CumulationService {
    private final CumulationRepo cumulationRepo;
    public CumulationService(CumulationRepo cumulationRepo) {
        this.cumulationRepo = cumulationRepo;
    }

    public List<Cumulation> getCumulationsByAccountLogin(String login) {
        return cumulationRepo.findByAccount_Login(login);
    }

    public CumulationRepo getCumulationRepo() {
        return cumulationRepo;
    }

    @Transactional
    public void addCumulation(CumulationDTO cumulationDTO, CumulationRepo cumulationRepo,String login, AccountRepo accountRepo) {
        Account account = accountRepo.findByLogin(login);
        if(account != null){
            Cumulation cumulation = new Cumulation();
            cumulation.setAccount(account);
            cumulation.setSumm(cumulationDTO.getSumm());
            cumulation.setDescription(cumulationDTO.getDescription());
            cumulation.setStatus(true);
            cumulationRepo.save(cumulation);
        } else {
            throw new IllegalArgumentException("Account with login"+login+" not found");
        }
    }

    @Transactional
    public void toggleStatus(String login, CumulationRepo cumulationRepo, Long id) {
       List<Cumulation> cumulation = cumulationRepo.findByAccount_Login(login);
       for(Cumulation c : cumulation){
           if (c.getId().equals(id)) {
               c.setStatus(!c.getStatus());
               cumulationRepo.save(c);
           }
       }


    }
    @Transactional
    public void deleteCumulation(Long id, CumulationRepo cumulationRepo, String login) {
        Cumulation cumulation = cumulationRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cumulation with id " + id + " not found"));

        if (!cumulation.getAccount().getLogin().equals(login)) {
            throw new AccessDeniedException("Ви не маєте доступу до цього накопичення.");
        }

        cumulationRepo.delete(cumulation);
    }

}
