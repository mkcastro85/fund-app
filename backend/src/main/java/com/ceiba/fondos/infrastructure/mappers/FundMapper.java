package com.ceiba.fondos.infrastructure.mappers;

import com.ceiba.fondos.domain.models.Fund;
import com.ceiba.fondos.infrastructure.repositories.entities.FundEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FundMapper {

    FundMapper INSTANCE = Mappers.getMapper(FundMapper.class);
    Fund toDomain(FundEntity entity);
    FundEntity toEntity(Fund domain);
    List<Fund> toDomainList(List<FundEntity> entities);
    List<FundEntity> toEntityList(List<Fund> domains);
}
