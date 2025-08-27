package com.ceiba.fondos.infrastructure.repositories.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "funds")
public class FundEntity {
    private int id;
    private String name;
    private long minAmount;
    private String category;
}
