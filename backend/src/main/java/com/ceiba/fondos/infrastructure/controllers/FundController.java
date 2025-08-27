package com.ceiba.fondos.infrastructure.controllers;

import com.ceiba.fondos.application.FundApplication;
import com.ceiba.fondos.domain.models.Fund;
import com.ceiba.fondos.domain.models.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funds")
public class FundController {

    private final FundApplication fundApplication;

    public FundController(FundApplication fundApplication) {
        this.fundApplication = fundApplication;
    }


    @PutMapping("/subscribe/{clientId}/{fundId}")
    public Transaction subscribe(@PathVariable String clientId,
                                 @PathVariable String fundId,
                                 @RequestParam String notification) {
        return fundApplication.subscribe(clientId,fundId,notification);
    }

    @PutMapping("/cancel/{clientId}/{fundId}")
    public Transaction cancel(@PathVariable String clientId,
                           @PathVariable String fundId) {
        return fundApplication.cancel(clientId,fundId);
    }

    @GetMapping("/transactions")
    public List<Transaction> transactions() {
        return fundApplication.getTransactions();
    }

    @GetMapping
    public List<Fund> funds() {
        return fundApplication.getFunds();
    }
}
