package com.ceiba.fondos.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fund {
    private String id;
    private String name;
    private Double minAmount;
    private String category;
}
