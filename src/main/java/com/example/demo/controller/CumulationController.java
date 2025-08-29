package com.example.demo.controller;


import com.example.demo.dto.CumulationDTO;
import com.example.demo.model.Cumulation;
import com.example.demo.repo.AccountRepo;
import com.example.demo.service.CumulationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class CumulationController {

    private final CumulationService cumulationService;
    private final AccountRepo accountRepo;

    public CumulationController(CumulationService cumulationService, AccountRepo accountRepo) {
        this.cumulationService = cumulationService;
        this.accountRepo = accountRepo;
    }

    @GetMapping("/cumulationList")
    public String cumulationList(Model model, Principal principal) {
        String login = principal.getName();
        List<Cumulation> cumulations = cumulationService.getCumulationRepo().findByAccount_Login(login);
        model.addAttribute("cumulations", cumulations);
       return "cumulationList";
    }

    @GetMapping("/startCumulatiom")
    public String startCumulation() {
        return "startCumulation";
    }


    @PostMapping("/startCumulation")
    public String startCumulation(@ModelAttribute CumulationDTO cumulationDTO,Principal principal) {
        cumulationService.addCumulation(cumulationDTO, cumulationService.getCumulationRepo(), principal.getName(), accountRepo);
        return "redirect:/cumulationList";
    }

    @PostMapping("/cumulationList/{id}")
    public String toggleStatusofCumulation(@PathVariable("id") Long id, Principal principal) {
        String login = principal.getName();
        cumulationService.toggleStatus(login,cumulationService.getCumulationRepo(), id);
        return "redirect:/cumulationList";
    }

    @PostMapping("/cumulationList/delete/{id}")
    public String deleteCumulation(@PathVariable("id") Long id, Principal principal) {
        String login = principal.getName();
        cumulationService.deleteCumulation(id,cumulationService.getCumulationRepo(), login);
        return "redirect:/cumulationList";
    }


}
