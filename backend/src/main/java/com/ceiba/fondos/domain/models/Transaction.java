package com.ceiba.fondos.domain.models;

import com.ceiba.fondos.domain.models.enums.TransactionTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Transaction {
    private String id;
    private Fund fund;
    private Double amount;
    private TransactionTypeEnum type;
    private Instant timestamp;

}
