package com.investCalc.controllers;

import com.investCalc.model.Entity.InvestmentEntity;
import com.investCalc.model.services.FundService;
import com.investCalc.model.services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FundController {

    private final FundService fundService;

    private final InvestmentService investmentService;

    @Autowired
    public FundController(FundService fundService, InvestmentService investmentService) {
        this.fundService = fundService;
        this.investmentService = investmentService;
    }

    @GetMapping("/funds")
    public String listFunds(@Param("sort") String sort, Model model) {
        model.addAttribute("funds", investmentService.getAllInvestments(sort));
        return "investments";
    }

    @GetMapping("/funds/{id}")
    public String getFund(@PathVariable Long id, Model model) {
        model.addAttribute("fund", investmentService.getInvestment(id));
        return "fund";
    }

    @GetMapping("/funds/new")
    public String showCreateForm(Model model) {
        model.addAttribute("investment", new InvestmentEntity());
        return "create_fund";
    }

    @PostMapping("/funds")
    public String createFund(@RequestParam String name, @RequestParam double amount) {
        investmentService.createInvestment(name, amount);
        return "redirect:/funds";
    }

    @DeleteMapping("/fund/{id}")
    public String deleteFund(@PathVariable Long id) {
        investmentService.deleteInvestment(id);
        return "redirect:/funds";
    }
}
