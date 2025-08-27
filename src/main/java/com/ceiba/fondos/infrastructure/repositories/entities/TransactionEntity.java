package com.ceiba.fondos.infrastructure.repositories.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "transactions")
public class TransactionEntity {
    @Id
    private String id;
    private int fundId;
    private long amount;
    private String type;
    private LocalDateTime date;

}
