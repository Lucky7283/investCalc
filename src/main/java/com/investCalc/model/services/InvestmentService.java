package com.investCalc.model.services;

import com.investCalc.model.Entity.InvestmentEntity;
import com.investCalc.model.Repositorys.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final FundService fundService;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    public InvestmentService(InvestmentRepository investmentRepository, FundService fundService) {
        this.investmentRepository = investmentRepository;
        this.fundService = fundService;
    }


    public List<InvestmentEntity> getAllInvestments(String sortBy) {
        Sort sort = switch (sortBy != null ? sortBy : "date") {
            case "amountAsc" -> Sort.by(Sort.Direction.ASC, "amount");
            case "amountDesc" -> Sort.by(Sort.Direction.DESC, "amount");
            case "name" -> Sort.by(Sort.Direction.ASC, "name");
            default -> Sort.by(Sort.Direction.DESC, "createdAt");
        };
        return investmentRepository.findAll(sort);
    }

    public InvestmentEntity getInvestment(Long id) {
        return investmentRepository.findById(id).orElse(null);
    }

    public String createInvestment(String name, Double amount) {
        if (amount <= 0) return "Amount must be positive";
        if (amount > fundService.getAvailableFund()) return "Insufficient funds";

        InvestmentEntity investment = new InvestmentEntity();
        investment.setName(name);
        investment.setAmount(amount);
        investment.setCreatedAt(LocalDateTime.now());

        investmentRepository.save(investment);

        scheduler.schedule(() -> {
            System.out.println("NOTIFICATION: New investment '" + name + "' of $" + amount + " has been successfully processed!");
        }, 2, TimeUnit.MINUTES);

        return "Success";
    }

    public void updateInvestmentName(Long id, String newName) {
        investmentRepository.findById(id).ifPresent(inv -> {
            inv.setName(newName);
            investmentRepository.save(inv);
        });
    }
}
