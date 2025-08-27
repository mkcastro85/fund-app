package com.ceiba.fondos.infrastructure.repositories;

import com.ceiba.fondos.infrastructure.repositories.entities.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<ClientEntity, String> {
}
