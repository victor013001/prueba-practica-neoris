package com.neoris.evalart.prueba_practica_neoris.application.mapper;

import com.neoris.evalart.prueba_practica_neoris.domain.model.Branch;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.BranchDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.Objects;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface BranchMapper {
    BranchDto toBranchDto(Branch branch);

    @Mapping(target = "products", ignore = true)
    Branch toBranch(BranchDto branchDto);

    @AfterMapping
    default void setUuid(@MappingTarget Branch branch) {
        if (Objects.isNull(branch.getUuid())) {
            branch.setUuid(UUID.randomUUID().toString());
        }
    }
}
