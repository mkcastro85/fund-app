package com.ceiba.fondos.infrastructure.repositories;

import com.ceiba.fondos.infrastructure.repositories.entities.TransactionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<TransactionEntity, String> {
}
