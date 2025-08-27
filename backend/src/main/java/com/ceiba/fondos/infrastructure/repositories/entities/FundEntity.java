package com.ceiba.fondos.infrastructure.repositories.entities;

import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "funds")
public class FundEntity {
    @Id
    private String id;
    private String name;
    private Double minAmount;
    private String category;
}
