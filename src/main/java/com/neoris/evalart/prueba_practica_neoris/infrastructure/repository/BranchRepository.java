package com.neoris.evalart.prueba_practica_neoris.infrastructure.repository;

import com.neoris.evalart.prueba_practica_neoris.domain.model.Branch;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BranchRepository extends ReactiveCrudRepository<Branch, Long> {
    Mono<Boolean> existsByName(String branchName);
}
