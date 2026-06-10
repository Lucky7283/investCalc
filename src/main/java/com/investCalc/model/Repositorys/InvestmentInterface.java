package com.investCalc.model.Repositorys;

import com.investCalc.model.Entity.InvestmentEntity;
import java.util.List;

public interface InvestmentInterface {
    InvestmentEntity findById(long id);
    List<InvestmentEntity> findAll(String sortBy);
    void save(InvestmentEntity investment);
    void updateName(long id, String newName);
}
