package com.ceiba.fondos.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fund {
    private int id;
    private String name;
    private long minAmount;
    private String category;
}
