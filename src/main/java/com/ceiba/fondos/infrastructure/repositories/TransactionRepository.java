package com.ceiba.fondos.infrastructure.repositories;

import com.ceiba.fondos.domain.models.Fund;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FundRepository extends MongoRepository<Fund, String> {
}
