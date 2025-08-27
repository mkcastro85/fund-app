package com.ceiba.fondos.application;

import com.ceiba.fondos.domain.models.Fund;
import com.ceiba.fondos.domain.models.Transaction;
import com.ceiba.fondos.domain.services.FundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FundApplication {

    private final FundService fundService;

    public FundApplication(FundService fundService) {
        this.fundService = fundService;
    }

    public Transaction subscribe(String client, String fund, String notification) {
        log.info("Excecuting subscribe with {}",fund);
        return fundService.subscribe(client, fund, notification);
    }

    public Transaction cancel(String client,String transactionId) {
        log.info("Excecuting cancel with {}", transactionId);
        return fundService.cancel(client,transactionId);
    }

    public List<Transaction> getTransactions() {
        log.info("Excecuting getTransactions");
        return fundService.getTransactions();
    }

    public List<Fund> getFunds(){
        log.info("Excecuting getFunds");
        return fundService.getFunds();
    }
}
