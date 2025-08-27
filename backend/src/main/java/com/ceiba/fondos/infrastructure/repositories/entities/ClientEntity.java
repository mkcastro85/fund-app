package com.ceiba.fondos.infrastructure.repositories.entities;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "clients")
public class ClientEntity {
    @Id
    private String id;
    private String name;
    private Double balance;
    private List<FundEntity> funds;
}
