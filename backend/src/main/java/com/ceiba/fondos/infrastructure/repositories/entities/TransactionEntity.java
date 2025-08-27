package com.ceiba.fondos.infrastructure.repositories.entities;

import com.ceiba.fondos.domain.models.enums.TransactionTypeEnum;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "transactions")
public class TransactionEntity {
    @Id
    private String id;
    private FundEntity fund;
    private Double amount;
    private TransactionTypeEnum type;
    private LocalDateTime date;

}
