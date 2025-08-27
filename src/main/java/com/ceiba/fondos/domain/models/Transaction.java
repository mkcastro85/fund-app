package com.ceiba.fondos.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Transaction {
    private String id;
    private int fundId;
    private long amount;
    private String type;
    private Instant timestamp;

}
