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

    /**
     * Check if the Franchise Uuid exists and returns the Id
     *
     * @param uuid The Franchise Uuid
     * @return Publisher that emits the Franchise Id
     */
    Mono<Long> getFranchiseIdByUuid(String uuid);

    /**
     * Check if the new Franchise name is different from the current and if is unique
     *
     * @param franchiseUuid The Franchise uuid
     * @param name The new Franchise name
     */
    Mono<Void> updateFranchiseName(String franchiseUuid, String name);
}
