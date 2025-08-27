package com.ceiba.fondos.domain.services;

import com.ceiba.fondos.domain.models.Fund;
import com.ceiba.fondos.domain.models.Transaction;
import java.util.List;
public interface FundService {
    Transaction subscribe(String client, String fund, String notification);
    Transaction cancel(String client,String fundId);
    List<Transaction> getTransactions();
    List<Fund> getFunds();
}
