package com.investCalc.model.Repositorys;

import com.investCalc.model.Entity.FundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundRepository extends JpaRepository<FundEntity, Long> {
}
