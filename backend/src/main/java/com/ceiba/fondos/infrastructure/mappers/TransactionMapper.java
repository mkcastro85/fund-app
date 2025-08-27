package com.ceiba.fondos.infrastructure.mappers;

import com.ceiba.fondos.domain.models.Fund;
import com.ceiba.fondos.domain.models.Transaction;
import com.ceiba.fondos.infrastructure.repositories.entities.FundEntity;
import com.ceiba.fondos.infrastructure.repositories.entities.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);
    Transaction toDomain(TransactionEntity entity);
    TransactionEntity toEntity(Transaction domain);
    List<Transaction> toDomainList(List<TransactionEntity> entities);
    List<TransactionEntity> toEntityList(List<Transaction> domains);
}
