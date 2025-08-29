package com.example.demo.controller;


import com.example.demo.dto.TotalMoneyDTO;
import com.example.demo.model.TotalMoney;
import com.example.demo.service.TotalMoneyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class TotalMoneyController {

    private final TotalMoneyService totalMoneyService;

    public TotalMoneyController(TotalMoneyService totalMoneyService) {
        this.totalMoneyService = totalMoneyService;
    }

//    @GetMapping("/totalMoneyInfo")
//    public String totalMoneyInfo(Model model, Principal principal, @ModelAttribute TotalMoneyDTO totalMoneyDTO) {
//        String login = principal.getName();
//        totalMoneyService.addTotalMoney(totalMoneyService.getTotalMoneyRepo(), totalMoneyDTO);
//        Optional<TotalMoney> totalMoneyOoop = totalMoneyService.getTotalMoneyRepo().findByAccount_Login(login);
//        TotalMoney totalMoney =totalMoneyOoop.get();
//        model.addAttribute("totalMoney", totalMoney);
//        return "totalMoneyInfo";
//    }

    @GetMapping("/totalMoneyInfo")
    public String totalMoneyInfo(Model model, Principal principal) {
        String login = principal.getName();
        totalMoneyService.updateOrCreateTotalMoneyForUser(login);
        TotalMoney totalMoney = totalMoneyService.getTotalMoneyRepo().findByAccount_Login(login).orElse(null);
        model.addAttribute("totalMoney", totalMoney);
        return "totalMoneyInfo";
    }


}
