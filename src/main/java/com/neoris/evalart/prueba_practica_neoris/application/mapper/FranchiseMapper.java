package com.neoris.evalart.prueba_practica_neoris.application.mapper;

import com.neoris.evalart.prueba_practica_neoris.domain.model.Franchise;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.FranchiseDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.Objects;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {BranchMapper.class})
public interface FranchiseMapper {
    FranchiseDto toFranchiseDto(Franchise franchise);

    @Mapping(target = "branches", ignore = true)
    Franchise toFranchise(FranchiseDto franchiseDto);

    @AfterMapping
    default void setUuid(@MappingTarget Franchise franchise) {
        if (Objects.isNull(franchise.getUuid())) {
            franchise.setUuid(UUID.randomUUID().toString());
        }
    }
}
