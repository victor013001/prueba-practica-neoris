package com.neoris.evalart.prueba_practica_neoris.application.mapper;

import com.neoris.evalart.prueba_practica_neoris.domain.model.Product;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.ProductDto;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.ProductRequestDto;
import org.mapstruct.AfterMapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.Objects;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    ProductDto toProductDto(Product product);

    @Mapping(target = "stock", defaultValue = "1")
    Product toProduct(ProductDto productDto);

    @Mapping(target = "stock", defaultValue = "1")
    @Mapping(target = "branchId", source = "branchId")
    Product toProduct(ProductRequestDto productRequestDto, Long branchId);

    @AfterMapping
    default void setUuid(@MappingTarget Product product) {
        if (Objects.isNull(product.getUuid())) {
            product.setUuid(UUID.randomUUID().toString());
        }
    }
}
