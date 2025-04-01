package com.neoris.evalart.prueba_practica_neoris.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ProductDto(
        UUID uuid,
        @NotBlank(message = "Name is mandatory")
        String name,
        @Positive(message = "Stock must be a positive value")
        Integer stock
) {
}
