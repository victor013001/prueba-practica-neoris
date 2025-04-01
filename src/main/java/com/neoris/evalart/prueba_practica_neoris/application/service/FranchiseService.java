package com.neoris.evalart.prueba_practica_neoris.application.service;

import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.FranchiseDto;
import reactor.core.publisher.Mono;

public interface FranchiseService {
    /**
     * Creates a new franchise in the database
     *
     * @param franchiseDto The validated FranchiseDto
     * @return Publisher that emits the saved FranchiseDto with UUID
     */
    Mono<FranchiseDto> createFranchise(FranchiseDto franchiseDto);
}
