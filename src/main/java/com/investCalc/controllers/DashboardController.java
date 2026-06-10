package com.investCalc.controllers;

import com.investCalc.model.services.FundService;
import com.investCalc.model.services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DashboardController {
    private final FundService fundService;
    private final InvestmentService investmentService;

    @Autowired
    public DashboardController(FundService fundService, InvestmentService investmentService) {
        this.fundService = fundService;
        this.investmentService = investmentService;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) String sort) {
        model.addAttribute("totalFund", fundService.getTotalFund());
        model.addAttribute("availableFund", fundService.getAvailableFund());
        model.addAttribute("investments", investmentService.getAllInvestments(sort));
        return "index";
    }

    @PostMapping("/investments")
    public String createInvestment(@RequestParam String name, @RequestParam double amount, Model model) {
        String result = investmentService.createInvestment(name, amount);
        if (!"Success".equals(result)) {
            model.addAttribute("error", result);
            return index(model, null);
        }
        return "redirect:/";
    }

    @PostMapping("/fund/add")
    public String addToFund(@RequestParam double amount) {
        fundService.addToFund(amount);
        return "redirect:/";
    }

    @GetMapping("/investments/{id}")
    public String detail(@PathVariable long id, Model model) {
        model.addAttribute("investment", investmentService.getInvestment(id));
        return "detail";
    }

    @PostMapping("/investments/{id}/update-name")
    public String updateName(@PathVariable long id, @RequestParam String newName) {
        investmentService.updateInvestmentName(id, newName);
        return "redirect:/investments/" + id;
    }
}
