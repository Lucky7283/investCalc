package com.investCalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication()
public class InvestCalcApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestCalcApplication.class, args);
    }

}
