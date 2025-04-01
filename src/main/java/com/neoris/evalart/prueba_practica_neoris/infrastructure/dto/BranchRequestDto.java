package com.neoris.evalart.prueba_practica_neoris.infrastructure.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record BranchRequestDto(
        String uuid,
        @NotBlank(message = "Name is mandatory")
        String name,
        @Valid
        List<ProductDto> products,
        @NotBlank(message = "Franchise Uuid is mandatory")
        String franchiseUuid
) {
}
