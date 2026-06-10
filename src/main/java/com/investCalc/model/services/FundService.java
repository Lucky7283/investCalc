package com.investCalc.model.services;

import com.investCalc.model.Entity.FundEntity;
import com.investCalc.model.Repositorys.FundRepository;
import com.investCalc.model.Repositorys.InvestmentRepository;
import com.investCalc.model.Entity.InvestmentEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundService {

    private final FundRepository fundRepository;
    private final InvestmentRepository investmentRepository;

    @Autowired
    public FundService(FundRepository fundRepository, InvestmentRepository investmentRepository) {
        this.fundRepository = fundRepository;
        this.investmentRepository = investmentRepository;
    }

    @PostConstruct
    public void init() {
        if (fundRepository.count() == 0) {
            fundRepository.save(new FundEntity(10000000.0));
        }
    }

    public Double getTotalFund() {
        return fundRepository.findAll().stream().findFirst()
                .map(FundEntity::getAmount)
                .orElse(0.0);
    }

    public Double getAvailableFund() {
        Double total = getTotalFund();
        List<InvestmentEntity> investments = investmentRepository.findAll();
        Double spent = investments.stream().mapToDouble(InvestmentEntity::getAmount).sum();
        return total - spent;
    }

    public void addToFund(Double amount) {
        FundEntity fund = fundRepository.findAll().stream().findFirst().orElse(new FundEntity(0.0));
        fund.setAmount(fund.getAmount() + amount);
        fundRepository.save(fund);
    }
}
