package com.neoris.evalart.prueba_practica_neoris.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductDto(
        @NotBlank(message = "Uuid is mandatory")
        String uuid,
        @NotBlank(message = "Name is mandatory")
        String name,
        @Positive(message = "Stock must be a positive value")
        Integer stock
) {
}
