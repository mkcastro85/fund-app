package com.ceiba.fondos.infrastructure.repositories;

import com.ceiba.fondos.infrastructure.repositories.entities.FundEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FundRepository extends MongoRepository<FundEntity, String> {
}
