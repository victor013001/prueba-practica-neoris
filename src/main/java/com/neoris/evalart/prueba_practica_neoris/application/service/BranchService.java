package com.neoris.evalart.prueba_practica_neoris.application.service;

import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.BranchDto;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.BranchRequestDto;
import reactor.core.publisher.Mono;

public interface BranchService {
    /**
     * Creates a new Branch in the database
     *
     * @param BranchRequestDto The validated BranchRequestDto with franchise Uuid
     * @return Publisher that emits the saved BranchDto with UUID
     */
    Mono<BranchDto> createBranch(BranchRequestDto BranchRequestDto);

    /**
     * Check if the Branch Uuid exists and returns the Id
     *
     * @param uuid The Branch Uuid
     * @return Publisher that emits the Branch Id
     */
    Mono<Long> getBranchIdByUuid(String uuid);

    /**
     * Check if the new Branch name is different from the current and if not exist
     *
     * @param branchUuid The Branch uuid
     * @param name The new Branch name
     */
    Mono<Void> updateBranchName(String branchUuid, String name);
}
