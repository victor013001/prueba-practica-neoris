package com.neoris.evalart.prueba_practica_neoris.infrastructure.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public record FranchiseDto(
        UUID uuid,
        @NotBlank(message = "Name is mandatory")
        String name,
        @Valid
        List<BranchDto> branches
) {
}
